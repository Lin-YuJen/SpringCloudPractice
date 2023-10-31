package com.enix.consumer.controller

import com.enix.common.json.CodeMessage
import com.enix.common.json.ResponseJson
import com.enix.common.util.LoggerUtil
import com.enix.consumer.loadBalance.CustomRoundRobinRule
import com.google.gson.JsonObject
import com.netflix.discovery.EurekaClient
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@Controller
@RequestMapping("/order")
class OrderController(
    val restTemplate: RestTemplate,
    val eurekaClient: EurekaClient,
    val discoveryClient: DiscoveryClient,
    val customRoundRobinRule: CustomRoundRobinRule
) {

    private val log = LoggerUtil.getLogger<OrderController>()
    private val PAYMENT_SERVICE_HOST_NAME = System.getenv("PAYMENT_SERVICE_HOST_NAME") ?: "PAYMENT-SERVICE"
    private val CREATE_PAYMENT_URL = "/payment/create"
    private val GET_PAYMENT_URL = "/payment/get"

    @ResponseBody
    @GetMapping("/get/{id}")
    fun getOrder(
        @PathVariable("id") id: Long
    ): ResponseJson {
//        val instanceInfo = eurekaClient.getNextServerFromEureka(PAYMENT_SERVICE_HOST_NAME, false)
//        val url = "http://${instanceInfo.ipAddr}:${instanceInfo.port}$GET_PAYMENT_URL/$id"
        val url = "http://$PAYMENT_SERVICE_HOST_NAME$GET_PAYMENT_URL/$id"

        log.info { "Get Url = $url" }

//        val entity = restTemplate.getForEntity(url, ResponseJson::class.java)
//        val responseCode = entity.statusCode
//        val responseBodyJson = entity.body

        return try {
            restTemplate.getForObject(
                url,
                ResponseJson::class.java
            )!!
        } catch (e: Exception) {
            log.error(e.message, e)
            ResponseJson().setStatus(CodeMessage.FAILURE).addData<JsonObject> {
                addProperty("error-message", e.message)
            }
        }
    }

    @ResponseBody
    @PostMapping("/create")
    fun createOrder(
        @RequestBody requestJson: JsonObject
    ): ResponseJson {
        val instanceInfo = eurekaClient.getNextServerFromEureka(PAYMENT_SERVICE_HOST_NAME, false)
        val url = "http://${instanceInfo.ipAddr}:${instanceInfo.port}$CREATE_PAYMENT_URL"

        val responseJson = ResponseJson()
        val responseEntity = restTemplate.postForEntity(url, requestJson, ResponseJson::class.java)
        responseJson.setStatus(
            when {
                responseEntity.statusCode.is2xxSuccessful -> CodeMessage.SUCCESS
                else -> CodeMessage.FAILURE
            }
        )
        responseJson.data = responseEntity.body.data
        return responseJson
    }

    /**
     * 使用自訂的輪巡規則取得要打 API 的服務。
     * 記得 Resttemplate 的 LoadBalanced Annotation 要拿掉。
     */
    @ResponseBody
    @GetMapping("/getRoundRobin/{id}")
    fun getOrderByCustomRobinRule(
        @PathVariable("id") id: Long
    ): ResponseJson {

        // 取得 Payment 服務的服務實體列表
        val instanceServiceList = discoveryClient.getInstances(PAYMENT_SERVICE_HOST_NAME)
        val hostname = when {
            instanceServiceList.isNullOrEmpty() -> PAYMENT_SERVICE_HOST_NAME
            else -> {
                val instance = customRoundRobinRule.getInstance(instanceServiceList)
                "${instance?.host}:${instance?.port}"
            }
        }

        val url = "http://$hostname$GET_PAYMENT_URL/$id"
        log.info { "Get Url = $url" }

        val entity = restTemplate.getForEntity(url, ResponseJson::class.java)
        val responseCode = entity.statusCode
        val responseBodyJson = entity.body

        return responseBodyJson
    }
}
package com.enix.consumer.controller

import com.enix.common.json.CodeMessage
import com.enix.common.json.ResponseJson
import com.enix.common.util.LoggerUtil
import com.enix.consumer.feignClient.PaymentFeignClient
import com.google.gson.JsonObject
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 使用 OpenFeign 來做微服物功能調用
 */
@Controller
@RequestMapping("/order")
class OrderController(
    val paymentFeignClient: PaymentFeignClient,
) {

    private val log = LoggerUtil.getLogger<OrderController>()

    @ResponseBody
    @GetMapping("/get/{id}")
    fun getOrder(
        @PathVariable("id") id: Long
    ): ResponseJson {
        log.info("Get payment $id")
        return paymentFeignClient.getPayment(id)
    }

    @ResponseBody
    @CircuitBreaker(
        name = "order-breaker-2",
        fallbackMethod = "fallback"
    )
    @GetMapping("/breaker/{milliseconds}")
    fun getBreakerOrder(
        @PathVariable("milliseconds") milliseconds: Long
    ): ResponseJson {
        log.info("Start to sleep ${milliseconds} milliseconds")
        Thread.sleep(milliseconds)
        val paymentResponseJson = paymentFeignClient.getPayment(1)
        return ResponseJson().setStatus(CodeMessage.SUCCESS).addData<JsonObject> {
            addProperty("message", "Success to sleep ${milliseconds} milliseconds.")
            if (paymentResponseJson.code == 200)
                add("payment", paymentResponseJson.data)
            else
                addProperty("payment", paymentResponseJson.message)
        }
    }

    fun fallback(
        throwable: Throwable
    ) = ResponseJson().setStatus(CodeMessage.FAILURE).addData<JsonObject> {
        addProperty("message", "Fallback method has been invoked. ${throwable.message}")
    }
}
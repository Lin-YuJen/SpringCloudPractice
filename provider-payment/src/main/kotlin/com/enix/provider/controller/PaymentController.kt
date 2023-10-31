package com.enix.provider.controller

import com.enix.common.json.CodeMessage.*
import com.enix.common.json.ResponseJson
import com.enix.common.util.LoggerUtil
import com.enix.provider.entity.PaymentEntity
import com.enix.provider.repository.PaymentRepository
import com.enix.provider.service.TracingService
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class PaymentController(
    val paymentRepository: PaymentRepository,
    val discoveryClient: DiscoveryClient,
    val tracingService: TracingService
) {

    val log = LoggerUtil.getLogger<PaymentController>()

    @Value("\${server.port}")
    lateinit var portNumber: String

    /**
     * 透過 ID 取出 Payment 資料
     */
    @GetMapping("/get/{id}")
    fun getPaymentById(
        @PathVariable("id") id: Long
    ): ResponseJson {
        log.info { "Get Payment by ID = $id" }
        val responseJson = ResponseJson()
        try {
            val payment = paymentRepository.getReferenceById(id)
            responseJson.addData<JsonObject> {
                addProperty("id", payment.id)
                addProperty("serial", payment.serial)
                addProperty("portNumber", portNumber.toInt())
            }
            responseJson.setStatus(SUCCESS)
        } catch (e: EntityNotFoundException) {
            responseJson.setStatus(FAILURE)
            responseJson.message = e.message ?: ""
        }
        return responseJson
    }

    @PostMapping("/create")
    fun createPayment(
        @RequestBody
        requestJson: JsonObject
    ): ResponseJson {
        log.info { "Insert new Payment. Request data is $requestJson" }
        val responseJson = ResponseJson()
        val newId = requestJson["id"].asLong
        val newSerial = requestJson["serial"].asString

        val newPayment = PaymentEntity(newId, newSerial)

        val resultPayment = paymentRepository.save(newPayment)
//        val resultPayment = paymentRepository.createPayment(newPayment)
        responseJson.setStatus(SUCCESS)
        responseJson.addData<JsonObject> {
            addProperty("id", resultPayment.id)
            addProperty("serial", resultPayment.serial)
        }
        return responseJson
    }

    @GetMapping("/discovery")
    fun discovery() = ResponseJson().setStatus(SUCCESS).addData<JsonArray> {
        discoveryClient.services.forEach { serviceName ->
            this.add(JsonObject().apply {
                addProperty("service-name", serviceName)
                add("instances-list", JsonArray().apply {
                    discoveryClient.getInstances(serviceName).forEach { serviceInstance ->
                        add(JsonObject().apply {
                            addProperty("service-id", serviceInstance.serviceId)
                            addProperty("instance-id", serviceInstance.instanceId)
                            addProperty("uri", serviceInstance.uri.toString())
                        })
                    }
                })
            })
        }
    }

    @GetMapping("/hello")
    fun tracing() = ResponseJson().setStatus(SUCCESS).addData<JsonObject> {
        addProperty("message", tracingService.sayHello())
    }
}
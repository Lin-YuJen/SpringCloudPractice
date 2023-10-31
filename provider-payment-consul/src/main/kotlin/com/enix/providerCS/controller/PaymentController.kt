package com.enix.providerCS.controller

import com.enix.common.json.CodeMessage
import com.enix.common.json.ResponseJson
import com.enix.common.util.LoggerUtil
import com.google.gson.JsonObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/payment")
class PaymentController(
    val discoveryClient: DiscoveryClient
) {

    val log = LoggerUtil.getLogger<PaymentController>()

    @Value("\${server.port}")
    lateinit var portNumber: String

    @ResponseBody
    @GetMapping("/get/{id}")
    fun getPaymentById(
        @PathVariable("id") id: Long
    ): ResponseJson {
        val responseJson = ResponseJson()
            .setStatus(CodeMessage.SUCCESS)
            .addData<JsonObject> {
                addProperty("number-port", portNumber.toInt())
            }
        return responseJson
    }
}
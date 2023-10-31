package com.enix.message.controller

import com.enix.common.json.CodeMessage
import com.enix.common.json.ResponseJson
import com.google.gson.JsonObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/message")
class MessageController(
    val streamBridge: StreamBridge
) {

    @Value("\${server.port:Unknown}")
    lateinit var port: String

    @Value("\${custom.binding-name:my-message}")
    lateinit var bindingName: String

    @GetMapping("/send/{message}")
    fun sendMessage(
        @PathVariable("message") message: String?
    ): ResponseJson = ResponseJson()
        .setStatus(
            when (streamBridge.send(bindingName, message)) {
                true -> CodeMessage.SUCCESS
                false -> CodeMessage.FAILURE
            }
        )
        .addData<JsonObject> {
            addProperty("port", port.toInt())
            addProperty("message", message)
        }
}
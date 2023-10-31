package com.enix.config.controller

import com.enix.common.json.CodeMessage
import com.enix.common.json.ResponseJson
import com.google.gson.JsonObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/info")
@RestController
@RefreshScope
class ClientController {

    @Value("\${config.version:Unknown}")
    lateinit var version: String

    @Value("\${config.enviroment:Unknown}")
    lateinit var enviroment: String

    @GetMapping("/config")
    fun getConfigInfo(): ResponseJson = ResponseJson()
        .setStatus(CodeMessage.SUCCESS)
        .addData<JsonObject> {
            addProperty("enviroment", enviroment)
            addProperty("version", version)
        }
}
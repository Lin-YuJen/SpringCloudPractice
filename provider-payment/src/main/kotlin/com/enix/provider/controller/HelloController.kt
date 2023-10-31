package com.enix.provider.controller

import com.enix.common.json.ResponseJson
import com.google.gson.JsonObject
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/test")
class HelloController {

    @ResponseBody
    @GetMapping("hello")
    fun sayHello() = "Hello, provider"

    @ResponseBody
    @GetMapping("/json")
    fun sayJson() = ResponseJson(0).addData<JsonObject> {
        addProperty("Name", "Jack")
    }
}
package com.enix.provider.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class CustomErrorController(
    errorAttributes: ErrorAttributes,
    serverProperties: ServerProperties
) : BasicErrorController(
    errorAttributes,
    serverProperties.error
) {

    /**
     * 取代預設的 Json Response
     */
    override fun error(request: HttpServletRequest): ResponseEntity<MutableMap<String, Any>> {

        // val bodyMap = getErrorAttributes(request, ErrorAttributeOptions.defaults())
        // 預設內容有四個：
        // timestamp = Thu Aug 11 18:10:55 CST 2022
        // status = 404
        // error = Not Found
        // path = /url
        return when (getStatus(request)) {
            HttpStatus.NO_CONTENT -> ResponseEntity(getStatus(request))
            else -> {
                val status = getStatus(request)
                return ResponseEntity(
                    mutableMapOf(
                        "code" to status.value().toString(),
                        "message" to status.reasonPhrase,
                        "data" to mutableMapOf<String, Any>()
                    ),
                    getStatus(request)
                )
            }
        }
    }
}
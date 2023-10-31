package com.enix.message

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageProviderApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<MessageProviderApplication>()
    log.info { "Start MessageProviderApplication." }
    runApplication<MessageProviderApplication>(*args)
}
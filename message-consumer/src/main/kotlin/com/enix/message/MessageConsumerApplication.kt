package com.enix.message

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageConsumerApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<MessageConsumerApplication>()
    log.info { "Start MessageConsumerApplication." }
    runApplication<MessageConsumerApplication>()
}
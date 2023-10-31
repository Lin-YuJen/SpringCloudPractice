package com.enix.gateway

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<GatewayApplication>()
    log.info { "Start GatewayApplication." }
    runApplication<GatewayApplication>(*args)
}
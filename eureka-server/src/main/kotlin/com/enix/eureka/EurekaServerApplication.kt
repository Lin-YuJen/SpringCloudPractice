package com.enix.eureka

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer // Eureka server 標註
class EurekaServerApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<EurekaServerApplication>()
    log.info { "Start EurekaServerApplication." }
    runApplication<EurekaServerApplication>(*args)
}
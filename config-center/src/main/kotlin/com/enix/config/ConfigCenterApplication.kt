package com.enix.config

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigCenterApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<ConfigCenterApplication>()
    log.info { "Start ConfigCenterApplication." }
    runApplication<ConfigCenterApplication>(*args)
}
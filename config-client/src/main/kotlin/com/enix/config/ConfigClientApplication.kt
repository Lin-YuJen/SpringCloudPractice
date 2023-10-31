package com.enix.config

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConfigClientApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<ConfigClientApplication>()
    log.info { "Start ConfigClientApplication." }
    runApplication<ConfigClientApplication>(*args)
}
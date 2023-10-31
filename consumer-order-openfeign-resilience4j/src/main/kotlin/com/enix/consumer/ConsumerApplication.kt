package com.enix.consumer

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

/**
 * 使用 OpenFeign 需要多這個 EnableFeignClients Annotation
 */
@EnableFeignClients
@SpringBootApplication
class ConsumerApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<ConsumerApplication>()
    log.info { "Start ConsumerApplication." }
    runApplication<ConsumerApplication>(*args)
}
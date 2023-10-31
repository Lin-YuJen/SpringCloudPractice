package com.enix.provider

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 * 注意：@EnableEurekaClient 已被廢棄不使用，也不需要再使用就可以生效。
 * 使用 EnableDiscoveryClient 開啟服務探索的功能，可以透過 DiscoveryClient 獲取 Eureka Server 上已註冊服務的資訊。
 */
@SpringBootApplication
@EnableDiscoveryClient
class ProviderApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<ProviderApplication>()
    log.info { "Start ProviderApplication." }
    runApplication<ProviderApplication>(*args)
}
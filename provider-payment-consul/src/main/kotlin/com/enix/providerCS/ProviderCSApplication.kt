package com.enix.providerCS

import com.enix.common.util.LoggerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 * 使用 EnableDiscoveryClient 開啟服務探索的功能，可以透過 DiscoveryClient 獲取 Eureka Server 上已註冊服務的資訊。
 */
@SpringBootApplication
@EnableDiscoveryClient
class ProviderCSApplication

fun main(args: Array<String>) {
    val log = LoggerUtil.getLogger<ProviderCSApplication>()
    log.info { "Start ProviderCSApplication." }
    runApplication<ProviderCSApplication>(*args)
}
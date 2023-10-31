package com.enix.consumer.config

import feign.Feign
import feign.Logger
import feign.Request
import feign.Retryer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit


@Configuration
class OpenFeignConfig {

    /**
     * 設定 OpenFeign 相關的 log 級別。
     * 另外需搭配 application.yml 中的設定要印出的 FeignClient 是哪一個。
     */
    @Bean
    fun feignLoggerLevel() = Logger.Level.FULL

    @Bean
    fun feignBuilder(): Feign.Builder = Feign.builder().apply {
        options(
            // 設定 Timeout 時間
            Request.Options(
                5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS, true
            )
        )
        retryer(Retryer.NEVER_RETRY)
    }
}
package com.enix.consumer.config

import com.enix.common.util.LoggerUtil
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class HttpConfiguration(
    val context: ApplicationContext
) {

    private val log = LoggerUtil.getLogger<HttpConfiguration>()

    /**
     * RestTemplate 增加 Gson 的轉換器
     */
    @Bean
//    @LoadBalanced // 給予 RestTemplate 負載均衡的功能
    fun restTemplate() = RestTemplate().apply {
        messageConverters = listOf(
            context.getBean(GsonHttpMessageConverter::class.java)
        )
    }
}
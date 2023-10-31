package com.enix.message.service

import com.enix.common.util.LoggerUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class MessageService {

    private val log = LoggerUtil.getLogger<MessageService>()

    @Bean
    fun messageConsumer() = Consumer<String> {
        log.info { "Got message : $it" }
    }
}
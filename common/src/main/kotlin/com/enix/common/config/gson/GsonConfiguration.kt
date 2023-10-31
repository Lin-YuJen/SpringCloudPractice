package com.enix.common.config.gson

import com.enix.system.config.gson.adapter.InstantAdapter
import com.enix.system.config.gson.adapter.LocalDateTimeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Instant
import java.time.LocalDateTime

@Configuration
class GsonConfiguration {

    @Bean
    fun gson(): Gson {
        return GsonBuilder().run {
            registerTypeAdapter(Instant::class.java, InstantAdapter())
            registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            create()
        }
    }
}
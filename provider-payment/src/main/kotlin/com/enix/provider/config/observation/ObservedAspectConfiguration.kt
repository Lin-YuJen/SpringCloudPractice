package com.enix.provider.config.observation

import com.enix.provider.config.observation.handler.SimpleLoggingHandler
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.aop.ObservedAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObservedAspectConfiguration {

    /**
     * 建立一個 AOP 的 Aspect
     */
    @Bean
    fun observedAspect(
        observationRegistry: ObservationRegistry
    ): ObservedAspect {
        observationRegistry.observationConfig().apply {
            // Register a handler
            observationHandler(SimpleLoggingHandler())
        }

        return ObservedAspect(observationRegistry)
    }
}
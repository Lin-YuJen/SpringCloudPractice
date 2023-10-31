package com.enix.provider.config.observation.handler

import com.enix.common.util.LoggerUtil
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationHandler
import org.springframework.stereotype.Component

/**
 * This component logs the observation lifecycle events,
 * from Created through Started until Stopped.
 */
@Component
class SimpleLoggingHandler : ObservationHandler<Observation.Context> {

    private val log = LoggerUtil.getLogger<SimpleLoggingHandler>()
    override fun supportsContext(context: Observation.Context): Boolean {
        // 全支援
        return true
    }

    override fun onStart(context: Observation.Context) {
        log.info("Starting context {} ", context)
    }

    override fun onError(context: Observation.Context) {
        log.info("Error for context {} ", context)
    }

    override fun onEvent(event: Observation.Event, context: Observation.Context) {
        log.info("Event for context {} and event [ {} ]", context, event)
    }

    override fun onScopeOpened(context: Observation.Context) {
        log.info("Scope opened for context {} ", context)
    }

    override fun onScopeClosed(context: Observation.Context) {
        log.info("Scope closed for context {}", context)
    }

    override fun onStop(context: Observation.Context) {
        log.info("Stopping context {} ", context)
    }
}
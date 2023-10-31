package com.enix.common.util

import mu.KLogger
import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class LoggerUtil {
    companion object {

        fun getLogger(kClazz: KClass<out Any>): KLogger {
            val log = LoggerFactory.getLogger(kClazz.java)
            return KotlinLogging.logger(log)
        }

        fun getLogger(javaClassName: String): KLogger {
            val log = LoggerFactory.getLogger(javaClassName)
            return KotlinLogging.logger(log)
        }

        inline fun <reified T> getLogger(): KLogger {
            val log = LoggerFactory.getLogger(T::class.java)
            return KotlinLogging.logger(log)
        }
    }
}
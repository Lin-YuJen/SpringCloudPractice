package com.enix.provider.service

import io.micrometer.observation.annotation.Observed
import org.springframework.stereotype.Service

@Observed(name = "helloService")
@Service
class TracingService {

    fun sayHello() = "Hello."
}
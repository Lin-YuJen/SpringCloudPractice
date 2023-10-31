package com.enix.consumer.feignClient

import com.enix.common.json.ResponseJson
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(
    name = "\${feign-client.payment-service-host-name}",
)
interface PaymentFeignClient {

    @GetMapping("/payment/get/{id}")
    @CircuitBreaker(name = "")
    @RateLimiter(name = "")
    fun getPayment(
        @PathVariable("id") id: Long
    ): ResponseJson
}
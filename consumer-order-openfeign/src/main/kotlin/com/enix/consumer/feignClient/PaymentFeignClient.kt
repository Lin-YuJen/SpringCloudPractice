package com.enix.consumer.feignClient

import com.enix.common.json.ResponseJson
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient("\${feign-client.payment-service-host-name}")
interface PaymentFeignClient {

    @GetMapping("/payment/get/{id}")
    fun getPayment(
        @PathVariable("id") id: Long
    ): ResponseJson
}
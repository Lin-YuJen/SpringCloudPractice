package com.enix.consumer.controller

import com.enix.common.json.ResponseJson
import com.enix.common.util.LoggerUtil
import com.enix.consumer.feignClient.PaymentFeignClient
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 使用 OpenFeign 來做微服物功能調用
 */
@Controller
@RequestMapping("/order")
class OrderController(
    val paymentFeignClient: PaymentFeignClient
) {

    private val log = LoggerUtil.getLogger<OrderController>()

    @ResponseBody
    @GetMapping("/get/{id}")
    fun getOrder(
        @PathVariable("id") id: Long
    ): ResponseJson {
        log.info("Get payment $id")
        return paymentFeignClient.getPayment(id)
    }
}
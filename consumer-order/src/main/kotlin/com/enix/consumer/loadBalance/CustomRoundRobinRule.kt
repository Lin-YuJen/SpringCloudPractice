package com.enix.consumer.loadBalance

import com.enix.common.util.LoggerUtil
import org.springframework.cloud.client.ServiceInstance
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class CustomRoundRobinRule {

    private val count = AtomicInteger(0)
    private val log = LoggerUtil.getLogger<CustomRoundRobinRule>()

    /**
     * 取得下一個 index
     */
    fun getAndIncrement(): Int {
        var nextCount: Int
        do {
            val currentCount = count.get()
            nextCount = if (currentCount > Int.MAX_VALUE) 0 else currentCount + 1
        } while (!count.compareAndSet(currentCount, nextCount))
        log.info("NextCount is $nextCount")
        return nextCount
    }

    /**
     * 自給定的服務列表中取得下一個服務實體
     */
    fun getInstance(serviceInstanceList: List<ServiceInstance>) = when {
        serviceInstanceList.isNullOrEmpty() -> null
        else -> {
            val index = getAndIncrement() % serviceInstanceList.size
            log.info("Next index = $index")
            serviceInstanceList[index]
        }
    }
}
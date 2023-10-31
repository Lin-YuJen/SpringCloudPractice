package com.enix.gateway.filter

import com.enix.common.util.LoggerUtil
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalGatewayFilter : GlobalFilter, Ordered {

    private val log = LoggerUtil.getLogger<GlobalGatewayFilter>()

    /**
     * 注意這已經是 Webflux 的寫法了
     * 這裡不深究這個部分，僅需知道可以透過物件去自訂 Filter
     */
    override fun filter(
        exchange: ServerWebExchange,
        chain: GatewayFilterChain
    ): Mono<Void> {
        val request = exchange.request
        val response = exchange.response
        if (!request.queryParams.containsKey("user")) {
            log.info { "Unknown user." }
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE)
            return response.setComplete()
        }

        // 放行
        return chain.filter(exchange)
    }

    /**
     * Filter 優先順位設定，越小越前面
     * 可以設定最小 -2147483648 到最大 2147483648
     */
    override fun getOrder() = 0

}
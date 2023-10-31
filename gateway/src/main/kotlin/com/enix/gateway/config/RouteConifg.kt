package com.enix.gateway.config

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConifg {

    /**
     * 直接使用物件的方式進行 Route 設定。
     */
    @Bean
    fun googleRouteLocator1(
        routeLocatorBuilder: RouteLocatorBuilder
    ) = routeLocatorBuilder.routes {
        route("googleHomePage") {
            path("/google")
            uri("https://www.google.co.jp/")
        }
    }
}
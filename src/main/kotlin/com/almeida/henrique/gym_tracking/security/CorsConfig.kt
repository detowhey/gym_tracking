package com.almeida.henrique.gym_tracking.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*


@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
class CorsConfig {

    @Bean
    fun corsFilterRegistrationBean(): FilterRegistrationBean<CorsFilter>? {
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOriginPatterns = listOf("*")
        config.allowedMethods = listOf("*")
        config.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        val bean: FilterRegistrationBean<CorsFilter> = FilterRegistrationBean<CorsFilter>()
        bean.filter = CorsFilter(source)
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}
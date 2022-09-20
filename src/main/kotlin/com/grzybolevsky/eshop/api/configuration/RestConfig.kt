package com.grzybolevsky.eshop.api.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@ComponentScan
class RestConfig {
    @Value("\${app.corsOrigins:}")
    private val corsOrigins: String = ""

    @Bean
    @Suppress("SpreadOperator")
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                val allowedOrigins = corsOrigins.split(",")
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedHeaders("*")
                    .allowedOriginPatterns(*allowedOrigins.toTypedArray())
                    .allowCredentials(true)
            }
        }
    }
}

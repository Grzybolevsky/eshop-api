package com.grzybolevsky.eshop.api.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension

@Bean
fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension {
    return SecurityEvaluationContextExtension()
}
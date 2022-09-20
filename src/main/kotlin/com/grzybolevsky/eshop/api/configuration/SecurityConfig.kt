package com.grzybolevsky.eshop.api.configuration

import com.grzybolevsky.eshop.api.users.auth.oauth2.OAuth2UserRegistrationService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension
import org.springframework.security.web.SecurityFilterChain

@Profile("security")
@Configuration
class SecurityConfig(private val oAuth2UserRegistrationService: OAuth2UserRegistrationService) {
    @Value("\${CLIENT_URL:http://localhost:3000}")
    private lateinit var clientUrl: String

    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension = SecurityEvaluationContextExtension()

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http {
            cors {}
            csrf {
                disable()
            }
            authorizeRequests {
                authorize("/products", permitAll)
                // authorize("/auth/login/**", permitAll)
                // authorize("/oauth2/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                // authenticationEntryPoint = HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
            }
            logout {
                logoutUrl = "/auth/logout"
                logoutSuccessUrl = clientUrl
                invalidateHttpSession = true
                deleteCookies("JSESSIONID")
            }
            oauth2Login {
                // loginPage = "/auth/login"
                userInfoEndpoint {
                    userService = oAuth2UserRegistrationService
                }
                defaultSuccessUrl(clientUrl, true)
            }
        }
        return http.build()
    }
}

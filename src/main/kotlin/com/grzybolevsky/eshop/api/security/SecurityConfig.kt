package com.grzybolevsky.eshop.api.security

import com.grzybolevsky.eshop.api.security.oauth2.OAuth2UserRegistrationService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Profile("security")
@Configuration
class SecurityConfig(private val oAuth2UserRegistrationService: OAuth2UserRegistrationService) {
    @Value("\${app.clientUrl:}")
    private val clientUrl: String = ""

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http {
            cors { }
            csrf {
                disable()
            }
            authorizeRequests {
                authorize("/products", permitAll)
                authorize("/user/logged", permitAll)
                authorize(anyRequest, authenticated)
            }
            logout {
                logoutUrl = "/auth/logout"
                logoutSuccessUrl = clientUrl
                invalidateHttpSession = true
                deleteCookies("JSESSIONID")
            }
            oauth2Login {
                userInfoEndpoint {
                    userService = oAuth2UserRegistrationService
                }
                defaultSuccessUrl(clientUrl, true)
            }
        }
        return http.build()
    }
}

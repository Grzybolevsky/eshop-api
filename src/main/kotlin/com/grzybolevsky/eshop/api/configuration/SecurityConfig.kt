package com.grzybolevsky.eshop.api.configuration

import com.grzybolevsky.eshop.api.users.auth.oauth2.OAuth2UserRegistrationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension
import org.springframework.security.web.SecurityFilterChain

@Profile("security")
@Configuration
class SecurityConfig(private val oAuth2UserRegistrationService: OAuth2UserRegistrationService) {
    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension = SecurityEvaluationContextExtension()

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http {
            //cors { }
            authorizeRequests {
                authorize("/", permitAll)
                authorize("/products", permitAll)
                authorize("/auth/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                //    authenticationEntryPoint = HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
            }
            logout {
                logoutSuccessUrl = "/"
                invalidateHttpSession = true
                deleteCookies("JSESSIONID")
            }
            oauth2Login {
                loginPage = "/auth/login"
                userInfoEndpoint {
                    userService = oAuth2UserRegistrationService
                }
            }
        }
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer { it.ignoring().antMatchers("/h2-console/**") }
}

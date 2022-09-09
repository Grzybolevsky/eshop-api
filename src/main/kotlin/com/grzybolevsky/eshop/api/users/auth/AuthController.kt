package com.grzybolevsky.eshop.api.users.auth

import com.grzybolevsky.eshop.api.users.User
import com.grzybolevsky.eshop.api.users.identity.IdentityService
import org.springframework.context.annotation.Profile
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RequestMapping("/auth")
@Profile("dev")
@RestController
class AuthController(private val identityService: IdentityService) {

    @GetMapping("/info")
    fun authInfo(@AuthenticationPrincipal principal: OAuth2User) = principal

    @GetMapping("/user")
    fun authUser(principal: Principal) = principal

    @GetMapping("/identity")
    fun identity(): User = identityService.getUser()
}
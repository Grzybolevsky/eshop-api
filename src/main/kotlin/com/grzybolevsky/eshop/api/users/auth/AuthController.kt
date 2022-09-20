package com.grzybolevsky.eshop.api.users.auth

import org.springframework.context.annotation.Profile
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@Profile("dev")
@RestController
class AuthController {

    @GetMapping("/info")
    fun authInfo(@AuthenticationPrincipal principal: OAuth2User) = principal
}

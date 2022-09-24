package com.grzybolevsky.eshop.api.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RequestMapping("/auth")
@RestController
class AuthController {
    @Value("\${app.clientUrl:}")
    private val clientUrl: String = ""

    @Profile("dev")
    @GetMapping("/info")
    fun authInfo(@AuthenticationPrincipal principal: OAuth2User) = principal

    @GetMapping("/authorized")
    fun authorize(request: HttpServletRequest, response: HttpServletResponse) {
        val cookie = Cookie("JSESSIONID", request.getAttribute("JSESSIONID") as String?)
        cookie.domain = URI(clientUrl).host
        cookie.path = "/"
        cookie.secure = false
        cookie.isHttpOnly = false
        response.addCookie(cookie)
        response.setHeader("Location", clientUrl)
        response.status = 302
    }
}

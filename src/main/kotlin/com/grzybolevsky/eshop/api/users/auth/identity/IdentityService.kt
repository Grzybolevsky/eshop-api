package com.grzybolevsky.eshop.api.users.auth.identity

import com.grzybolevsky.eshop.api.users.User
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope("session", proxyMode = ScopedProxyMode.TARGET_CLASS)
class IdentityService {
    private lateinit var user: User
    private var isSet = false

    fun getUser() = user

    fun setUser(user: User) {
        if (this::user.isInitialized) {
            throw IllegalStateException()
        }
        this.user = user
        this.isSet = true
    }

    val isNotSet get() = !this.isSet
}
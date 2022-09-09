package com.grzybolevsky.eshop.api.users.identity

import com.grzybolevsky.eshop.api.users.User
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope("session", proxyMode = ScopedProxyMode.TARGET_CLASS)
class IdentityService {
    private lateinit var user: User

    fun getUser(): User = user

    fun setUser(user: User) {
        if (this::user.isInitialized) {
            throw IllegalStateException()
        }
        this.user = user
    }
}
package com.grzybolevsky.eshop.api.users.auth.oauth2

import com.grzybolevsky.eshop.api.users.User
import com.grzybolevsky.eshop.api.users.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuth2UserRegistrationService(private val userRepository: UserRepository) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val user = super.loadUser(userRequest)
        processOAuthUser(user)
        return user
    }

    private fun processOAuthUser(user: OAuth2User) {
        val userEmail = user.attributes["email"].toString()

        if (!userRepository.existsByEmail(userEmail)) {
            registerNewUser(userEmail)
        }
    }

    private fun registerNewUser(userEmail: String) {
        userRepository.save(User(userEmail))
    }
}
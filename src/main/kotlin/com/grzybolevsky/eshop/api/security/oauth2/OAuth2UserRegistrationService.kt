package com.grzybolevsky.eshop.api.security.oauth2

import com.grzybolevsky.eshop.api.security.identity.IdentityService
import com.grzybolevsky.eshop.api.security.oauth2.info.getGithubUserEmail
import com.grzybolevsky.eshop.api.users.User
import com.grzybolevsky.eshop.api.users.UserDetailsRepository
import com.grzybolevsky.eshop.api.users.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuth2UserRegistrationService(
    private val userRepository: UserRepository,
    private val userDetailsRepository: UserDetailsRepository,
    private val identityService: IdentityService
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val user = super.loadUser(userRequest)
        processOAuthUser(user, userRequest)
        return user
    }

    private fun processOAuthUser(user: OAuth2User, request: OAuth2UserRequest?) {
        val email = if (user.attributes["email"] != null) {
            user.attributes["email"].toString()
        } else {
            when (request?.clientRegistration?.registrationId) {
                "github" -> getGithubUserEmail(request)
                else -> error("Cannot get email for Github user")
            }
        }
        if (!userRepository.existsByEmail(email)) {
            registerNewUser(email)
        } else if (identityService.isNotSet) {
            identityService.setUser(userRepository.findByEmail(email)!!)
        }
    }

    @Transactional
    fun registerNewUser(userEmail: String) {
        val user = User(userEmail)
        userDetailsRepository.save(user.details)
        userRepository.save(user)
        identityService.setUser(user)
    }
}

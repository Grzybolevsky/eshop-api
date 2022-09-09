package com.grzybolevsky.eshop.api.users.auth.oauth2

import com.grzybolevsky.eshop.api.baskets.Basket
import com.grzybolevsky.eshop.api.baskets.BasketRepository
import com.grzybolevsky.eshop.api.users.User
import com.grzybolevsky.eshop.api.users.UserDetailsRepository
import com.grzybolevsky.eshop.api.users.UserRepository
import com.grzybolevsky.eshop.api.users.identity.IdentityService
import com.grzybolevsky.eshop.api.users.auth.oauth2.info.getGithubUserEmail
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class OAuth2UserRegistrationService(
    private val userRepository: UserRepository,
    private val userDetailsRepository: UserDetailsRepository,
    private val basketRepository: BasketRepository,
    private val identityService: IdentityService
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val user = super.loadUser(userRequest)
        processOAuthUser(user, userRequest)
        return user
    }

    private fun processOAuthUser(user: OAuth2User, request: OAuth2UserRequest?) {
        val email = if (user.attributes["email"] != null) user.attributes["email"].toString()
        else when (request?.clientRegistration?.registrationId) {
            "github" -> getGithubUserEmail(request)
            else -> throw Exception()
        }
        if (!userRepository.existsByEmail(email)) {
            registerNewUser(email)
        } else {
            identityService.setUser(userRepository.findByEmail(email)!!)
        }
    }

    @Transactional
    fun registerNewUser(userEmail: String) {
        val user = User(userEmail)
        val basket = Basket(user)
        userDetailsRepository.save(user.details)
        userRepository.save(user)
        identityService.setUser(user)
        basketRepository.save(basket)
    }
}
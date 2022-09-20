package com.grzybolevsky.eshop.api.users

import com.grzybolevsky.eshop.api.users.auth.identity.IdentityService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userDetailsRepository: UserDetailsRepository,
    private val identityService: IdentityService
) {
    fun isLogged() = !identityService.isNotSet

    fun updateUserEmail(email: String): UserView {
        val userData = identityService.getUser()
        userData.email = email
        return userRepository.save(userData).toView()
    }

    fun updateUserDetails(userDetailsView: UserDetailsView): UserDetailsView {
        val details = identityService.getUser().details
        with(details) {
            firstName = userDetailsView.firstName
            secondName = userDetailsView.secondName
            address = userDetailsView.address
            city = userDetailsView.city
            postCode = userDetailsView.postCode
            phone = userDetailsView.phone
        }
        return userDetailsRepository.save(details).toView()
    }
}

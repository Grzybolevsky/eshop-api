package com.grzybolevsky.eshop.api.users

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findUserById(id: Long): User? {
        return userRepository.findUserById(id)
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}

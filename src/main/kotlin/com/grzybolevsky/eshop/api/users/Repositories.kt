package com.grzybolevsky.eshop.api.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun findUserById(id: Long): User?
}

@Repository
interface UserDetailsRepository : JpaRepository<UserDetails, Long>
package com.grzybolevsky.eshop.api.users

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

}

@Service
class ClientService(private val clientRepository: ClientRepository) {

}
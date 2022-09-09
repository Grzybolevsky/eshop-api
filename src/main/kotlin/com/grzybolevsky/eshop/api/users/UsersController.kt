package com.grzybolevsky.eshop.api.users

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(private val userService: UserService) {

}

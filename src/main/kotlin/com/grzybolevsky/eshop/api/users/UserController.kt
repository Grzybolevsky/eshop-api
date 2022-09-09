package com.grzybolevsky.eshop.api.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @GetMapping("/logged")
    fun isLogged() = userService.isLogged()

    @PutMapping("/details")
    fun updateUserDetails(@RequestBody userDetailsView: UserDetailsView) =
        userService.updateUserDetails(userDetailsView)

    @PutMapping("/{email}")
    fun updateUserEmail(@PathVariable email: String) = userService.updateUserEmail(email)
}

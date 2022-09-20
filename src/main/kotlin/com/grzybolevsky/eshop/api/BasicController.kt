package com.grzybolevsky.eshop.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class BasicController {
    private val greeting = "e-shop API"

    @GetMapping
    fun appName() = greeting
}

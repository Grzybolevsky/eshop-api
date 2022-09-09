package com.grzybolevsky.eshop.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class BasicController {

    @GetMapping
    fun appName(): String {
        return "e-shop api"
    }
}
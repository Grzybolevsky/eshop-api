package com.grzybolevsky.eshop.api.baskets

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/basket")
class BasketsController(private val basketService: BasketService) {

    @GetMapping
    fun getBasket() {

    }

    @PostMapping
    @PutMapping
    fun updateBasket() {

    }

    @DeleteMapping
    fun emptyBasket() {

    }
}

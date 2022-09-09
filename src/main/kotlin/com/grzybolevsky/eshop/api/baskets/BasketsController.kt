package com.grzybolevsky.eshop.api.baskets

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/basket")
class BasketsController(private val basketService: BasketService) {

    @GetMapping
    fun getBasket(): List<BasketProductView> = basketService.getBasket()

    @PostMapping("/{productId}")
    fun addProductToBasket(@PathVariable productId: Long) = basketService.addProductToBasket(productId)

    @DeleteMapping
    fun emptyBasket(): List<BasketProductView> = basketService.empty()

    @DeleteMapping("/{productId}")
    fun deleteProductFromBasket(@PathVariable productId: Long): BasketProductView = basketService.removeProductFromBasket(productId)
}

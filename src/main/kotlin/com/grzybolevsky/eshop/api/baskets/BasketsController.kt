package com.grzybolevsky.eshop.api.baskets

import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/basket")
class BasketsController(private val basketService: BasketService) {

    @GetMapping
    fun getBasket(): List<BasketProductView> = basketService.getBasket()

    @PostMapping("/{productId}")
    fun addProductToBasket(@PathVariable productId: Long) =
        basketService.addProductToBasket(productId)

    @PutMapping
    fun udpateProduct(
        @Valid @RequestBody
        basketProduct: BasketProductView,
    ): BasketProductView =
        basketService.updateBasketProduct(basketProduct)

    @DeleteMapping
    fun emptyBasket(): List<BasketProductView> = basketService.empty()

    @DeleteMapping("/{productId}")
    fun deleteProductFromBasket(@PathVariable productId: Long): BasketProductView =
        basketService.removeProductFromBasket(productId)
}

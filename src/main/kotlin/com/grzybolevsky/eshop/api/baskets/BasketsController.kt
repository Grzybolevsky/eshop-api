package com.grzybolevsky.eshop.api.baskets

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/basket")
class BasketsController(private val basketService: BasketService) {

    @GetMapping
    fun getBasket(): BasketView = basketService.getBasket()

    @PostMapping
    @PutMapping
    fun updateBasket(@RequestBody basketView: BasketView): BasketView {
        return basketService.update(basketView)
    }

    @DeleteMapping
    fun emptyBasket(): BasketView = basketService.empty()
}

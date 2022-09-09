package com.grzybolevsky.eshop.api.baskets

import org.springframework.stereotype.Service
import java.util.*

@Service
class BasketService(private val repository: BasketRepository) {
    fun getBasket(basketId: Long): Optional<Basket> = repository.findById(basketId)

    fun create(basketView: BasketView): Basket = repository.save(basketView.toEntity())
}
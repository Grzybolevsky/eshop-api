package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.users.identity.IdentityService
import org.springframework.stereotype.Service

@Service
class BasketService(private val repository: BasketRepository,
                    private val basketProductRepository: BasketProductRepository,
                    private val identityService: IdentityService) {
    fun getBasket(): BasketView = findUserBasket().toView()

    fun update(basketView: BasketView): BasketView = repository.save(basketView.toEntity(identityService.getUser())).toView()

    fun empty(): BasketView {
        val basket = findUserBasket()
        basketProductRepository.deleteAll(basket.basketProducts)
        return repository.save(basket).toView()
    }

    private fun findUserBasket() = repository.findByUserId(identityService.getUser().id!!)
}
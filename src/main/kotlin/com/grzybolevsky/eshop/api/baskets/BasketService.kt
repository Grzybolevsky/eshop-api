package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.products.ProductService
import com.grzybolevsky.eshop.api.users.auth.identity.IdentityService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BasketService(
    private val basketProductRepository: BasketProductRepository,
    private val productService: ProductService,
    private val identityService: IdentityService
) {
    fun getBasket(): List<BasketProductView> = getBasketRaw().map(BasketProduct::toView)

    fun getBasketRaw(): List<BasketProduct> =
        basketProductRepository.findAllByUserId(identityService.getUser().id!!)

    fun addProductToBasket(productId: Long): BasketProductView {
        val product = productService.getProduct(productId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val basketProduct = BasketProduct(product, 1, identityService.getUser())
        return basketProductRepository.save(basketProduct).toView()
    }

    fun removeProductFromBasket(basketProductId: Long): BasketProductView {
        val basketProduct = basketProductRepository.findByIdAndUserId(basketProductId, identityService.getUser().id!!)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        basketProductRepository.delete(basketProduct)
        return basketProduct.toView()
    }

    fun empty(): List<BasketProductView> {
        basketProductRepository.deleteAllByUserId(identityService.getUser().id!!)
        return emptyList()
    }
}
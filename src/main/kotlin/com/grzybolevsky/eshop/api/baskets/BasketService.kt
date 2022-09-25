package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.products.ProductService
import com.grzybolevsky.eshop.api.products.toEntity
import com.grzybolevsky.eshop.api.security.identity.IdentityService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class BasketService(
    private val basketProductRepository: BasketProductRepository,
    private val productService: ProductService,
    private val identityService: IdentityService
) {
    fun getBasket(): List<BasketProductView> = getBasketRaw().map(BasketProduct::toView)

    fun getBasketRaw(): List<BasketProduct> = basketProductRepository.findAllByUserId(identityService.getUser().id!!)

    @Transactional
    fun addProductToBasket(productId: Long): BasketProductView {
        val product = productService.getProduct(productId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val user = identityService.getUser()
        val basketProduct = basketProductRepository.findByProductIdAndUserId(productId, user.id!!) ?: BasketProduct(
            product.toEntity(), 0, identityService.getUser()
        )
        basketProduct.quantity += 1
        return basketProductRepository.save(basketProduct).toView()
    }

    @Transactional
    fun removeProductFromBasket(basketProductId: Long): BasketProductView {
        val basketProduct = basketProductRepository.findByIdAndUserId(basketProductId, identityService.getUser().id!!)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        basketProductRepository.delete(basketProduct)
        return basketProduct.toView()
    }

    @Transactional
    fun empty(): List<BasketProductView> {
        basketProductRepository.deleteAllInBatchByUserId(identityService.getUser().id!!)
        return emptyList()
    }

    @Transactional
    fun updateBasketProduct(basketProduct: BasketProductView): BasketProductView {
        return basketProductRepository.save(
            BasketProduct(
                basketProduct.product.toEntity(),
                basketProduct.quantity,
                identityService.getUser(),
                basketProduct.id
            )
        ).toView()
    }
}

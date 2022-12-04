package com.grzybolevsky.eshop.api.products

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService(val repository: ProductRepository) {
    fun getProduct(productId: Long): ProductView? = repository.findProductByIdAndActiveIsTrue(productId)?.toView()

    fun getProducts(): List<ProductView> = repository.findAllByActiveIsTrue().map(Product::toView).toList()

    @Transactional
    fun saveProduct(product: ProductView): ProductView = repository.save(product.toEntity()).toView()

    @Transactional
    fun deleteProduct(productId: Long): Product {
        val product =
            repository.findProductByIdAndActiveIsTrue(productId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        product.active = false
        return repository.save(product)
    }
}

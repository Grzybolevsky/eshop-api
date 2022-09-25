package com.grzybolevsky.eshop.api.products

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ProductService(val repository: ProductRepository) {
    fun getProduct(productId: Long): ProductView? = repository.findProductById(productId)?.toView()

    fun getProducts(): List<ProductView> = repository.findAll().map(Product::toView).toList()

    @Transactional
    fun saveProduct(product: ProductView): ProductView = repository.save(product.toEntity()).toView()

    @Transactional
    fun deleteProduct(productId: Long): ProductView = TODO("Not yet implemented")
}

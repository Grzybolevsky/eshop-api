package com.grzybolevsky.eshop.api.products

import org.springframework.stereotype.Service

@Service
class ProductService(val repository: ProductRepository) {
    fun getProduct(productId: Long): Product? = repository.findByProductId(productId)

    fun getProducts(): List<Product> = repository.findAll().toList()

    fun saveProduct(product: ProductView): Product = repository.save(product.toEntity())
}

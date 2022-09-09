package com.grzybolevsky.eshop.api.products

import org.springframework.data.repository.CrudRepository

interface ProductRepository: CrudRepository<Product, Long> {
    fun findByProductId(productId: Long): Product?
}
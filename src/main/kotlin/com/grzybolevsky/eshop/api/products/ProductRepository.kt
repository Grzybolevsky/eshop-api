package com.grzybolevsky.eshop.api.products

import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long> {
    fun findProductByIdAndActiveIsTrue(productId: Long): Product?
    fun findAllByActiveIsTrue(): List<Product>
}

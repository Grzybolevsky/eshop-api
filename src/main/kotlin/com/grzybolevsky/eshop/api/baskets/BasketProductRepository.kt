package com.grzybolevsky.eshop.api.baskets

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BasketProductRepository : JpaRepository<BasketProduct, Long> {
    fun findByIdAndUserId(id: Long, userId: Long): BasketProduct?
    fun findAllByUserId(userId: Long?): List<BasketProduct>
    fun deleteAllInBatchByUserId(userId: Long)
    fun findByProductIdAndUserId(productId: Long, userId: Long): BasketProduct?
}

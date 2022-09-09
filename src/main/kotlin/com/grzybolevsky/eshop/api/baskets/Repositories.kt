package com.grzybolevsky.eshop.api.baskets

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BasketRepository : JpaRepository<Basket, Long> {
    fun findByUserId(userId: Long): Basket
}

@Repository
interface BasketProductRepository : JpaRepository<BasketProduct, Long>
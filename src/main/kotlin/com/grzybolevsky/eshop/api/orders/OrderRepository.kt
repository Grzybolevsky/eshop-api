package com.grzybolevsky.eshop.api.orders

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findOrderById(orderId: Long): Order?
}

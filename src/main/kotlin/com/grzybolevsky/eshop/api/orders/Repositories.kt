package com.grzybolevsky.eshop.api.orders

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findOrderByIdAndUserId(orderId: Long, userId: Long): Order?
    fun findAllByUserId(userId: Long): List<Order>
}

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, Long> {
    fun findAllByOrderId(orderId: Long): List<OrderItem>
}

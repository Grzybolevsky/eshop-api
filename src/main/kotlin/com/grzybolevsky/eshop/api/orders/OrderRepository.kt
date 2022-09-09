package com.grzybolevsky.eshop.api.orders

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long> {
    fun findOrderByOrderId(orderId: Long): Order?
}

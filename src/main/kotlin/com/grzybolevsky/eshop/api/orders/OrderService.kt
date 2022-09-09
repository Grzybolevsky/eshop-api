package com.grzybolevsky.eshop.api.orders

import org.springframework.stereotype.Service

@Service
class OrderService(private val repository: OrderRepository) {
    fun getOrder(id: Long): Order? = repository.findOrderByOrderId(id)

    fun getOrders(): List<Order> = repository.findAll().toList()

    fun createOrUpdateOrder(orderView: OrderView): Order = repository.save(orderView.toEntity())
}

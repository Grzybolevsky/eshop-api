package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.users.identity.IdentityService
import org.springframework.stereotype.Service

@Service
class OrderService(private val repository: OrderRepository, private val identityService: IdentityService) {
    fun getOrder(id: Long): OrderView? = repository.findOrderById(id)?.toView()

    fun getOrders(): List<OrderView> = repository.findAll().map(Order::toView).toList()

    fun createOrUpdateOrder(orderView: OrderView): OrderView =
        repository.save(orderView.toEntity(identityService.getUser())).toView()
}

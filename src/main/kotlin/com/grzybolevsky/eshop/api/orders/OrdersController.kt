package com.grzybolevsky.eshop.api.orders

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/orders")
class OrdersController(private val orderService: OrderService) {

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Long): OrderView =
        orderService.getOrder(orderId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @GetMapping
    fun getOrders(): List<OrderView> = orderService.getOrders()

    @PostMapping
    fun createOrder(): OrderView = orderService.createOrder()

    @GetMapping("/{orderId}/items")
    fun getOrderItems(@PathVariable orderId: Long): List<OrderItemView> = orderService.getOrderItems(orderId)
}

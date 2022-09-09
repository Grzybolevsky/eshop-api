package com.grzybolevsky.eshop.api.orders

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/orders")
class OrdersController(private val orderService: OrderService) {

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Long): Order =
        orderService.getOrder(orderId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @GetMapping
    fun getOrders(): List<Order> = orderService.getOrders()

    @PostMapping
    fun createOrder(@RequestBody orderView: OrderView): Order = orderService.createOrUpdateOrder(orderView)
}

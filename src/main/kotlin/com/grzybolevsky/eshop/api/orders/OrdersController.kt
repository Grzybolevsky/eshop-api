package com.grzybolevsky.eshop.api.orders

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
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
    fun createOrder(@RequestBody orderView: OrderView): OrderView = orderService.createOrUpdateOrder(orderView)
}

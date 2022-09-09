package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.baskets.BasketService
import com.grzybolevsky.eshop.api.users.auth.identity.IdentityService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val basketService: BasketService,
    private val identityService: IdentityService
) {
    fun getOrder(orderId: Long): OrderView? = getOrderRaw(orderId)?.toView()

    fun getOrderRaw(orderId: Long): Order? =
        orderRepository.findOrderByIdAndUserId(orderId, identityService.getUser().id!!)

    fun getOrders(): List<OrderView> =
        orderRepository.findAllByUserId(identityService.getUser().id!!).map(Order::toView)

    fun getOrderItems(orderId: Long): List<OrderItemView> =
        orderItemRepository.findAllByOrderId(orderId).map { it.toView() }

    @Transactional
    fun createOrder(): OrderView {
        val order = Order(identityService.getUser())
        orderRepository.save(order)
        val orderItems = basketService.getBasketRaw().map { OrderItem(order, it.product, it.quantity) }
        orderItemRepository.saveAll(orderItems)
        basketService.empty()
        return order.toView()
    }
}

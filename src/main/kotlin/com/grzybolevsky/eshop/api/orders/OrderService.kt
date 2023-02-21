package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.baskets.BasketService
import com.grzybolevsky.eshop.api.payments.PaymentsService
import com.grzybolevsky.eshop.api.security.identity.IdentityService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val basketService: BasketService,
    private val identityService: IdentityService,
    private val paymentsService: PaymentsService,
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
        val orderPrice = basketService.getBasket().sumOf { it.totalPrice }
        val paymentLink = paymentsService.getPaymentlink(orderPrice)
        val order = Order(identityService.getUser(), false, paymentLink)
        orderRepository.save(order)
        val orderItems = basketService.getBasketRaw().map { OrderItem(order, it.product, it.quantity) }
        orderItemRepository.saveAll(orderItems)
        basketService.empty()
        return order.toView()
    }
}

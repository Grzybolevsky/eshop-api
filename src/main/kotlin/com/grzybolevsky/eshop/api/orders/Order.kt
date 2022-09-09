package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.baskets.Basket
import com.grzybolevsky.eshop.api.baskets.BasketView
import com.grzybolevsky.eshop.api.baskets.toEntity
import com.grzybolevsky.eshop.api.baskets.toView
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @OneToOne
    var basket: Basket,
    var createdAt: Instant = Instant.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var orderId: Long? = null
)

fun Order.toView() = OrderView(basket.toView(), createdAt)

class OrderView(
    var basketView: BasketView,
    var createdAt: Instant
)

fun OrderView.toEntity() = Order(basketView.toEntity(), createdAt)
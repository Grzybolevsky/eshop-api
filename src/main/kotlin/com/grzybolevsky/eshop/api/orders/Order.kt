package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.baskets.Basket
import com.grzybolevsky.eshop.api.baskets.BasketView
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
class Order(
    @OneToOne
    var basket: Basket,
    var createdAt: Instant = Instant.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null
)

class OrderView(
    var basketView: BasketView,
    var createdAt: Date
)

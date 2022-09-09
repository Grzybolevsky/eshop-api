package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.baskets.Basket
import com.grzybolevsky.eshop.api.baskets.BasketView
import com.grzybolevsky.eshop.api.baskets.toEntity
import com.grzybolevsky.eshop.api.baskets.toView
import com.grzybolevsky.eshop.api.users.User
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
    var id: Long? = null
)

class OrderItem(

)

fun Order.toView() = OrderView(basket.toView(), createdAt, id)

class OrderView(
    var basketView: BasketView,
    var createdAt: Instant,
    var id: Long?
)

fun OrderView.toEntity(user: User) = Order(basketView.toEntity(user), createdAt, id)
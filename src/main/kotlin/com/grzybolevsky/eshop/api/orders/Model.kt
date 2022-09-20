package com.grzybolevsky.eshop.api.orders

import com.grzybolevsky.eshop.api.products.Product
import com.grzybolevsky.eshop.api.products.ProductView
import com.grzybolevsky.eshop.api.products.toView
import com.grzybolevsky.eshop.api.users.User
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @OneToOne
    var user: User,
    var createdAt: Instant = Instant.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun Order.toView() = OrderView(createdAt, id)

@Entity
class OrderItem(
    @ManyToOne
    var order: Order,
    @ManyToOne
    var product: Product,
    var quantity: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun OrderItem.toView() = OrderItemView(product.toView(), quantity)

data class OrderItemView(
    val productView: ProductView,
    val quantity: Int
)

data class OrderView(
    val createdAt: Instant,
    val id: Long?
)

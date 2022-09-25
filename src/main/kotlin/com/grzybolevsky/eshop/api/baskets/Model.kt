package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.products.Product
import com.grzybolevsky.eshop.api.products.ProductView
import com.grzybolevsky.eshop.api.products.toView
import com.grzybolevsky.eshop.api.users.User
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.Valid
import javax.validation.constraints.Positive

@Entity
@Table(name = "basket_products")
class BasketProduct(
    @ManyToOne
    var product: Product,
    @field:Positive @field:Valid
    var quantity: Int,
    @ManyToOne
    var user: User,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun BasketProduct.toView() =
    BasketProductView(product.toView(), quantity, product.price * quantity.toBigDecimal(), id)

data class BasketProductView(
    val product: ProductView,
    @field:Positive
    val quantity: Int,
    @field:Positive
    val totalPrice: BigDecimal,
    val id: Long?
)

package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.products.Product
import com.grzybolevsky.eshop.api.products.ProductView
import com.grzybolevsky.eshop.api.products.toView
import com.grzybolevsky.eshop.api.users.User
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "basket_products")
class BasketProduct(
    @ManyToOne
    var product: Product,
    var quantity: Int,
    @ManyToOne
    var user: User,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun BasketProduct.toView() = BasketProductView(product.toView(), quantity, product.price, id)

data class BasketProductView(
    val product: ProductView,
    val quantity: Int,
    val totalPrice: BigDecimal,
    val id: Long?
)

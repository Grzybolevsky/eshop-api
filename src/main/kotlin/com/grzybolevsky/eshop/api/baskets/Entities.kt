package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.products.Product
import com.grzybolevsky.eshop.api.products.toView
import com.grzybolevsky.eshop.api.users.User
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "baskets")
class Basket(
    @OneToOne
    var user: User,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, targetEntity = BasketProduct::class)
    var basketProducts: List<BasketProduct> = ArrayList(),
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun Basket.toView() = BasketView(basketProducts.map(BasketProduct::toView))

@Entity
@Table(name = "basket_products")
class BasketProduct(
    @ManyToOne
    var product: Product,
    var quantity: Int,
    var totalPrice: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun BasketProduct.toView() = BasketProductView(product.toView(), quantity, totalPrice, id)
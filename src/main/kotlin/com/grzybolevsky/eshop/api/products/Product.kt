package com.grzybolevsky.eshop.api.products

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
class Product(
    var name: String,
    var category: String,
    var description: String,
    var price: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var productId: Long? = null
)

fun Product.toView() = ProductView(name, category, description, price, productId)

class ProductView(
    var name: String,
    var category: String,
    var description: String,
    var price: BigDecimal,
    var productId: Long?
)

fun ProductView.toEntity() = Product(name, category, description, price, productId)
package com.grzybolevsky.eshop.api.products

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "products")
class Product(
    var name: String,
    var category: String,
    var description: String,
    @PositiveOrZero
    var price: BigDecimal,
    val imageUrl: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun Product.toView() = ProductView(name, category, description, price, imageUrl, id)

class ProductView(
    var name: String,
    var category: String,
    var description: String,
    @PositiveOrZero
    var price: BigDecimal,
    val imageUrl: String,
    var id: Long?
)

fun ProductView.toEntity() = Product(name, category, description, price, imageUrl, id)

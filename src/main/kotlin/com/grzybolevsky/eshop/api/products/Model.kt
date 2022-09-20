package com.grzybolevsky.eshop.api.products

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
class Product(
    var name: String,
    var category: String,
    var description: String,
    var price: BigDecimal,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun Product.toView() = ProductView(name, category, description, price, id)

class ProductView(
    var name: String,
    var category: String,
    var description: String,
    var price: BigDecimal,
    var id: Long?
)

fun ProductView.toEntity() = Product(name, category, description, price, id)

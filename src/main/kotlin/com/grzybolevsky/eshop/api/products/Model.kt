package com.grzybolevsky.eshop.api.products

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "products")
class Product(
    @field:NotBlank
    val name: String,
    val category: String,
    val description: String,
    @field:PositiveOrZero
    val price: BigDecimal,
    val imageUrl: String,
    var active: Boolean = true,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)

fun Product.toView() = ProductView(name, category, description, price, imageUrl, id)

data class ProductView(
    @field:NotBlank
    val name: String,
    val category: String,
    val description: String,
    @field:PositiveOrZero
    val price: BigDecimal,
    val imageUrl: String,
    val id: Long?
)

fun ProductView.toEntity() = Product(name, category, description, price, imageUrl, true, id)

package com.grzybolevsky.eshop.api.products

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

@Suppress("LongParameterList")
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

package com.grzybolevsky.eshop.api.baskets

import com.grzybolevsky.eshop.api.products.ProductView
import com.grzybolevsky.eshop.api.products.toEntity
import java.math.BigDecimal

data class BasketView(
    val basketProducts: List<BasketProductView>
)

fun BasketView.toEntity() = Basket(basketProducts.map(BasketProductView::toEntity))

data class BasketProductView(
    val product: ProductView,
    val quantity: Int,
    val totalPrice: BigDecimal,
)

fun BasketProductView.toEntity() = BasketProduct(product.toEntity(), quantity, totalPrice)
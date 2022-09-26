package com.grzybolevsky.eshop.api.payments

import com.stripe.Stripe
import com.stripe.model.PaymentLink
import com.stripe.model.Price
import com.stripe.model.Product
import com.stripe.param.PaymentLinkCreateParams
import com.stripe.param.PriceCreateParams
import com.stripe.param.ProductCreateParams
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class PaymentsService {
    @Value("\${app.stripe:}")
    private val stripeApiKey: String = ""

    fun getPaymentlink(totalValue: BigDecimal): String {
        Stripe.apiKey = stripeApiKey

        val extractedPrice = (totalValue * (10).toBigDecimal()).toLong()

        val productParams = ProductCreateParams.builder()
            .setName("Order price")
            .setDescription("$12/Month subscription")
            .build()
        val product: Product = Product.create(productParams)

        val priceParams = PriceCreateParams
            .builder()
            .setProduct(product.id)
            .setCurrency("pln")
            .setUnitAmount(extractedPrice)
            .setRecurring(
                PriceCreateParams.Recurring
                    .builder()
                    .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                    .build()
            )
            .build()
        val price = Price.create(priceParams)

        val paymentLinkParams = PaymentLinkCreateParams
            .builder()
            .addLineItem(
                PaymentLinkCreateParams.LineItem
                    .builder()
                    .setPrice(price.id)
                    .setQuantity(1L)
                    .build()
            )
            .build()

        val paymentLink = PaymentLink.create(paymentLinkParams)

        return paymentLink.url
    }
}

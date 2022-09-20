package com.grzybolevsky.eshop.api.payments

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("payments")
class PaymentsController(private val paymentsService: PaymentsService) {
    fun getPayment() {
        paymentsService.getPayment()
    }
}

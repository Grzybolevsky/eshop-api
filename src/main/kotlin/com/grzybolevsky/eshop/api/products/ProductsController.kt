package com.grzybolevsky.eshop.api.products

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/products")
class ProductsController(private val productService: ProductService) {
    @GetMapping
    fun getAll(): List<Product> = productService.getProducts()

    @GetMapping("/{productId}")
    fun getOne(@PathVariable productId: Long): Product =
        productService.getProduct(productId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    fun createOrUpdate(@RequestBody productView: ProductView): ProductView =
        productService.saveProduct(productView).toView()
}

package com.grzybolevsky.eshop.api.products

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
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
    fun getAll(): List<ProductView> = productService.getProducts()

    @GetMapping("/{productId}")
    fun getOne(@PathVariable productId: Long) =
        productService.getProduct(productId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    fun createOrUpdate(
        @Valid @RequestBody
        productView: ProductView,
    ) =
        productService.saveProduct(productView)

    @DeleteMapping("/{productId}")
    fun delete(@PathVariable productId: Long) =
        productService.deleteProduct(productId)
}

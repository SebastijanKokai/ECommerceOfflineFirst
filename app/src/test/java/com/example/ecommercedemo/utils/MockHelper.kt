package com.example.ecommercedemo.utils

import com.example.ecommercedemo.domain.model.Product

fun getMockedProducts(): List<Product> {
    return listOf(
        Product(1, "Test product", 10.0, "", "", ""),
        Product(2, "Test product 2", 20.0, "", "", "")
    )
}

fun getMockedProduct(): Product {
    return Product(
        id = 1,
        title = "Mocked Product",
        price = 10.0,
        description = "A mocked product for testing",
        category = "mock",
        image = ""
    )
}
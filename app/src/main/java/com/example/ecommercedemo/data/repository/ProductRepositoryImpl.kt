package com.example.ecommercedemo.data.repository

import com.example.ecommercedemo.data.mapper.toDomain
import com.example.ecommercedemo.data.model.ProductResponse
import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository
import kotlinx.coroutines.delay

class ProductRepositoryImpl : ProductRepository {
    private val fakeProducts = listOf(
        ProductResponse("1", "Product 1", 12.99),
        ProductResponse("2", "Product 2", 7.99),
        ProductResponse("3", "Product 3", 3.5)
    )

    override suspend fun getProducts(): List<ProductDomain> {
        delay(500)
        return fakeProducts.toDomain()
    }

    override suspend fun getProductById(productId: String): ProductDomain? {
        delay(300)
        val product = fakeProducts.find { it.id == productId }
        return product?.toDomain()
    }
}
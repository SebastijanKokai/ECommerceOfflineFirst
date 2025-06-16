package com.example.ecommercedemo.domain.repository

import com.example.ecommercedemo.domain.model.ProductDomain

interface ProductRepository {
    suspend fun getProducts(): List<ProductDomain>
    suspend fun getProductById(productId: String): ProductDomain?
}
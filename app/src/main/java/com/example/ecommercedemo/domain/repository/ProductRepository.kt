package com.example.ecommercedemo.domain.repository

import com.example.ecommercedemo.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<ProductDomain>>
    suspend fun getProductById(productId: Int): ProductDomain?
    suspend fun refreshProducts()
}
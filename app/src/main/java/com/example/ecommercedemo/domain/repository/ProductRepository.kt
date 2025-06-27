package com.example.ecommercedemo.domain.repository

import com.example.ecommercedemo.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun getProductById(productId: Int): Product?
    suspend fun refreshProducts()
}
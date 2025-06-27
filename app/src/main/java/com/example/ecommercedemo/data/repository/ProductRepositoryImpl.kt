package com.example.ecommercedemo.data.repository

import com.example.ecommercedemo.data.local.dao.ProductDao
import com.example.ecommercedemo.data.mapper.toDomain
import com.example.ecommercedemo.data.remote.api.ProductApi
import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getProducts(): List<ProductDomain> {
        val response = productApi.getProducts()
        return response.toDomain()
    }

    override suspend fun getProductById(productId: Int): ProductDomain? {
        val response = productApi.getProductById(id = productId)
        return response.toDomain()
    }
}
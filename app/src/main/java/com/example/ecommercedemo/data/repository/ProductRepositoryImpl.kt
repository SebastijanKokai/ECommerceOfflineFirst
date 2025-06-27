package com.example.ecommercedemo.data.repository

import com.example.ecommercedemo.data.local.dao.ProductDao
import com.example.ecommercedemo.data.mapper.toDomain
import com.example.ecommercedemo.data.mapper.toEntity
import com.example.ecommercedemo.data.remote.api.ProductApi
import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val productDao: ProductDao
) : ProductRepository {
    override fun getProducts(): Flow<List<Product>> = productDao.getAll().toDomain()

    override suspend fun getProductById(productId: Int): Product? {
        val response = productApi.getProductById(id = productId)
        return response.toDomain()
    }

    override suspend fun refreshProducts() {
        runCatching {
            val response = productApi.getProducts()
            val entities = response.toEntity()
            productDao.insertAll(entities)
        }.onFailure { e ->
            print(e.toString())
            // @TODO Implement Error Logging
        }
    }
}
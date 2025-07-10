package com.example.ecommercedemo.data.repository

import com.example.ecommercedemo.core.dispatcher.CoroutineDispatcherProvider
import com.example.ecommercedemo.data.local.dao.ProductDao
import com.example.ecommercedemo.data.mapper.toDomain
import com.example.ecommercedemo.data.mapper.toEntity
import com.example.ecommercedemo.data.remote.api.ProductApi
import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val productApi: ProductApi,
    private val productDao: ProductDao
) : ProductRepository {
    override fun getProducts(): Flow<List<Product>> =
        productDao.getAll().toDomain().flowOn(dispatcherProvider.io)

    override suspend fun getProductById(productId: Int): Product? =
        withContext(dispatcherProvider.io) {
            val response = productApi.getProductById(id = productId)
            response.toDomain()
        }


    override suspend fun refreshProducts() = withContext(dispatcherProvider.io) {
        val response = productApi.getProducts()
        val entities = response.toEntity()
        productDao.insertAll(entities)
    }
}
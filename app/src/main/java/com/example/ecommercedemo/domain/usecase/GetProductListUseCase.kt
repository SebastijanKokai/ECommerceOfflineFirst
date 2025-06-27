package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductListUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Flow<List<Product>>>() {

    override suspend fun execute(params: Unit): Flow<List<Product>> {
        return productRepository.getProducts()
    }
}
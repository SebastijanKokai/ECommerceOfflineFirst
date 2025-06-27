package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductListUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Flow<List<ProductDomain>>>() {

    override suspend fun execute(params: Unit): Flow<List<ProductDomain>> {
        return productRepository.getProducts()
    }
}
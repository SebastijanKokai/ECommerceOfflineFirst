package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository

class GetProductDetailUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<String, ProductDomain?>() {

    override suspend fun execute(params: String): ProductDomain? {
        return productRepository.getProductById(params)
    }
}
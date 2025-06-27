package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository

class GetProductDetailUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Int, ProductDomain?>() {

    override suspend fun execute(params: Int): ProductDomain? {
        return productRepository.getProductById(params)
    }
}
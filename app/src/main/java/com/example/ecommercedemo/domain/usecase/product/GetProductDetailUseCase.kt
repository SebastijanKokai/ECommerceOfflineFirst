package com.example.ecommercedemo.domain.usecase.product

import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.domain.repository.ProductRepository
import com.example.ecommercedemo.domain.usecase.BaseUseCase

class GetProductDetailUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Int, Product?>() {

    override suspend fun execute(params: Int): Product? {
        return productRepository.getProductById(params)
    }
}
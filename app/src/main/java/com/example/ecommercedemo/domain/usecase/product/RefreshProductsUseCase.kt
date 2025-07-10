package com.example.ecommercedemo.domain.usecase.product

import com.example.ecommercedemo.domain.repository.ProductRepository
import com.example.ecommercedemo.domain.usecase.BaseUseCase

class RefreshProductsUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        return productRepository.refreshProducts()
    }
}
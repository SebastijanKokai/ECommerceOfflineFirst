package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.repository.ProductRepository

class RefreshProductsUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        return productRepository.refreshProducts()
    }
}
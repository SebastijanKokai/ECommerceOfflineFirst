package com.example.ecommercedemo.domain.usecase

import com.example.ecommercedemo.domain.model.ProductDomain
import com.example.ecommercedemo.domain.repository.ProductRepository

class GetProductListUseCase(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, List<ProductDomain>>() {

    override suspend fun execute(params: Unit): List<ProductDomain> {
        return productRepository.getProducts()
    }
}
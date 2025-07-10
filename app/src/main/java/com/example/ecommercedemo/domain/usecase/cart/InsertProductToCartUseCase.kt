package com.example.ecommercedemo.domain.usecase.cart

import com.example.ecommercedemo.domain.repository.CartRepository
import com.example.ecommercedemo.domain.usecase.BaseUseCase

class InsertProductToCartUseCase(
    private val cartRepository: CartRepository
) : BaseUseCase<Pair<Int, Int>, Unit>() {
    override suspend fun execute(params: Pair<Int, Int>) {
        cartRepository.insertProduct(productId = params.first, quantity = params.second)
    }
}
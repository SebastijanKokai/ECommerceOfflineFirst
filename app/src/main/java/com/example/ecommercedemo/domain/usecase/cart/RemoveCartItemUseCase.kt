package com.example.ecommercedemo.domain.usecase.cart

import com.example.ecommercedemo.domain.repository.CartRepository
import com.example.ecommercedemo.domain.usecase.BaseUseCase

class RemoveCartItemUseCase(private val cartRepository: CartRepository) :
    BaseUseCase<Int, Unit>() {
    override suspend fun execute(params: Int) {
        cartRepository.removeCartItem(params)
    }
}
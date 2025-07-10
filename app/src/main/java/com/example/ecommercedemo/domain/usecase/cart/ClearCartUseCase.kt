package com.example.ecommercedemo.domain.usecase.cart

import com.example.ecommercedemo.domain.repository.CartRepository
import com.example.ecommercedemo.domain.usecase.BaseUseCase

class ClearCartUseCase(private val cartRepository: CartRepository) : BaseUseCase<Unit, Unit>() {
    override suspend fun execute(params: Unit) {
        cartRepository.clearCart()
    }
}
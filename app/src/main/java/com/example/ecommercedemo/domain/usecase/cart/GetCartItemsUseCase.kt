package com.example.ecommercedemo.domain.usecase.cart

import com.example.ecommercedemo.data.local.entity.cart.CartItemWithProduct
import com.example.ecommercedemo.domain.repository.CartRepository
import com.example.ecommercedemo.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(
    private val cartRepository: CartRepository
) : BaseUseCase<Unit, Flow<List<CartItemWithProduct>>>() {
    override suspend fun execute(params: Unit): Flow<List<CartItemWithProduct>> {
        return cartRepository.getCartProducts()
    }
}
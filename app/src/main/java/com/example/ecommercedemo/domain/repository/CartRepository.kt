package com.example.ecommercedemo.domain.repository

import com.example.ecommercedemo.data.local.entity.cart.CartItemWithProduct
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartProducts(): Flow<List<CartItemWithProduct>>
    suspend fun insertProduct(productId: Int, quantity: Int)
    suspend fun removeCartItem(productId: Int)
    suspend fun clearCart()
}
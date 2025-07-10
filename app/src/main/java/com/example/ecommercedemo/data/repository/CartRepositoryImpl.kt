package com.example.ecommercedemo.data.repository

import com.example.ecommercedemo.core.dispatcher.CoroutineDispatcherProvider
import com.example.ecommercedemo.data.local.dao.CartDao
import com.example.ecommercedemo.data.local.entity.cart.CartItemEntity
import com.example.ecommercedemo.data.local.entity.cart.CartItemWithProduct
import com.example.ecommercedemo.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

// Uses only local data source, because the fake API isn't working as expected
class CartRepositoryImpl(
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val cartDao: CartDao,
) : CartRepository {
    // Get userId from DataStore - Auth not implemented for this project
    val userId = 1

    override fun getCartProducts(): Flow<List<CartItemWithProduct>> {
        return cartDao.getCartItemsWithProduct(userId)
    }

    override suspend fun insertProduct(productId: Int, quantity: Int) {
        withContext(dispatcherProvider.io) {
            val cartItem = cartDao.getCartItem(userId, productId).firstOrNull()

            if (cartItem == null) {
                cartDao.insertCartItem(
                    CartItemEntity(
                        id = 0,
                        userId = userId,
                        productId = productId,
                        quantity = quantity
                    )
                )
            } else {
                val updatedItem = cartItem.copy(quantity = cartItem.quantity + quantity)
                cartDao.insertCartItem(updatedItem)
            }

        }
    }

    override suspend fun clearCart() {
        withContext(dispatcherProvider.io) {
            cartDao.clearCart(userId)
        }
    }
}
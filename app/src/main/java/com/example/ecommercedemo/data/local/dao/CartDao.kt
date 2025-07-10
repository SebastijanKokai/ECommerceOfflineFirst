package com.example.ecommercedemo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.ecommercedemo.data.local.entity.cart.CartItemEntity
import com.example.ecommercedemo.data.local.entity.cart.CartItemWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item: CartItemEntity)

    @Transaction
    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    fun getCartItemsWithProduct(userId: Int): Flow<List<CartItemWithProduct>>

    @Query("DELETE FROM cart_items WHERE userId = :userId")
    suspend fun clearCart(userId: Int)
}
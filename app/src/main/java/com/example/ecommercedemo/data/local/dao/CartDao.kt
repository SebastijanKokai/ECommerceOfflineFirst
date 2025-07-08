package com.example.ecommercedemo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommercedemo.data.local.entity.CartEntity
import com.example.ecommercedemo.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item: CartItemEntity)

    @Query("SELECT * FROM cart_items WHERE cartId = :cartId")
    fun getCartItems(cartId: String): Flow<List<CartItemEntity>>

    @Query("DELETE FROM cart_items WHERE cartId = :cartId")
    suspend fun clearCart(cartId: String)
}
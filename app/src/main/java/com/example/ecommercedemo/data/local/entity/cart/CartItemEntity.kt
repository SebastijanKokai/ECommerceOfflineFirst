package com.example.ecommercedemo.data.local.entity.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,
    val productId: Int,
    val quantity: Int
)
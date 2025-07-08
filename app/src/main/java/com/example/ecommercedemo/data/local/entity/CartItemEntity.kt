package com.example.ecommercedemo.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart_items",
    foreignKeys = [ForeignKey(
        entity = CartEntity::class,
        parentColumns = ["cartId"],
        childColumns = ["cartId"],
        onDelete = CASCADE
    )],
    indices = [Index("cartId")]
)
data class CartItemEntity(
    @PrimaryKey val productId: Int,
    val cartId: String,
    val name: String,
    val image: String,
    val price: Double,
    val quantity: Int
)
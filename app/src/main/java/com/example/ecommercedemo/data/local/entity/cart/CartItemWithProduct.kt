package com.example.ecommercedemo.data.local.entity.cart

import androidx.room.Embedded
import androidx.room.Relation
import com.example.ecommercedemo.data.local.entity.product.ProductEntity

data class CartItemWithProduct(
    @Embedded val cartItem: CartItemEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: ProductEntity
)

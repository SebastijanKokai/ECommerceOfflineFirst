package com.example.ecommercedemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecommercedemo.data.local.dao.CartDao
import com.example.ecommercedemo.data.local.dao.ProductDao
import com.example.ecommercedemo.data.local.entity.cart.CartItemEntity
import com.example.ecommercedemo.data.local.entity.product.ProductEntity

@Database(
    entities = [ProductEntity::class, CartItemEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}
package com.example.ecommercedemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecommercedemo.data.local.dao.ProductDao
import com.example.ecommercedemo.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
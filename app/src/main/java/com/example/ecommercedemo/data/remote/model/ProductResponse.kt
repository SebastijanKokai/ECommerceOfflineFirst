package com.example.ecommercedemo.data.remote.model

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
)
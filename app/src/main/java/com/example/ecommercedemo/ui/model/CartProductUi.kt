package com.example.ecommercedemo.ui.model

data class CartProductUi(
    val image: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val totalPrice: Double
)
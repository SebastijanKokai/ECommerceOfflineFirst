package com.example.ecommercedemo.ui.cart.model

data class CartProductUi(
    val id: Int,
    val image: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val totalPrice: Double
)
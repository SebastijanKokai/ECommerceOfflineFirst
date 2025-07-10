package com.example.ecommercedemo.ui.mapper

import com.example.ecommercedemo.data.local.entity.cart.CartItemWithProduct
import com.example.ecommercedemo.ui.cart.model.CartProductUi

fun List<CartItemWithProduct>.toUiModel(): List<CartProductUi> {
    return map { itemWithProduct ->
        val product = itemWithProduct.product
        val cartItem = itemWithProduct.cartItem

        CartProductUi(
            id = product.id,
            image = product.image,
            name = product.title,
            price = product.price,
            quantity = cartItem.quantity,
            totalPrice = product.price * cartItem.quantity
        )
    }
}

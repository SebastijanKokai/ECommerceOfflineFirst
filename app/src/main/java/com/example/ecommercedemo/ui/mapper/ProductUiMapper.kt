package com.example.ecommercedemo.ui.mapper

import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.ui.model.ProductUi
import java.util.Locale


fun List<Product>.toUiModel() = this.map { it.toUiModel() }

fun Product.toUiModel() = ProductUi(
    id = id,
    name = title,
    price = String.format(Locale.ENGLISH, "%.2f", price),
    image = image,
)
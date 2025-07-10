package com.example.ecommercedemo.ui.mapper

import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.ui.productdetail.model.ProductDetailUi
import com.example.ecommercedemo.ui.productlist.model.ProductListUi
import java.util.Locale


fun List<Product>.toProductListUiModel() = this.map { it.toProductListUiModel() }

fun Product.toProductListUiModel() = ProductListUi(
    id = id,
    name = title,
    price = String.format(Locale.ENGLISH, "%.2f", price),
    image = image,
)

fun Product.toProductDetailUiModel() = ProductDetailUi(
    id = id,
    name = title,
    price = String.format(Locale.ENGLISH, "%.2f", price),
    description = description,
    category = category,
    image = image,
)
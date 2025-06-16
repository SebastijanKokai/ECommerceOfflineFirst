package com.example.ecommercedemo.data.mapper

import com.example.ecommercedemo.data.model.ProductResponse
import com.example.ecommercedemo.domain.model.ProductDomain

fun List<ProductResponse>.toDomain() = this.map { it.toDomain() }

fun ProductResponse.toDomain() = ProductDomain(
    id = id,
    name = name,
    price = price,
)

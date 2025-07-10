package com.example.ecommercedemo.data.mapper

import com.example.ecommercedemo.data.local.entity.product.ProductEntity
import com.example.ecommercedemo.data.remote.model.ProductResponse
import com.example.ecommercedemo.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun List<ProductResponse>.toDomain() = this.map { it.toDomain() }

fun ProductResponse.toDomain() = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    category = category,
    image = image,
)

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        category = category,
        image = image,
    )
}


fun Flow<List<ProductEntity>>.toDomain(): Flow<List<Product>> {
    return this.map { entityList: List<ProductEntity> -> entityList.map { it.toDomain() } }
}

fun ProductResponse.toEntity() = ProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
)

fun List<ProductResponse>.toEntity() = this.map { it.toEntity() }




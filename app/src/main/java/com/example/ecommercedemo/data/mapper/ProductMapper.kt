package com.example.ecommercedemo.data.mapper

import com.example.ecommercedemo.data.local.entity.ProductEntity
import com.example.ecommercedemo.data.remote.model.ProductResponse
import com.example.ecommercedemo.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun List<ProductResponse>.toDomain() = this.map { it.toDomain() }

fun ProductResponse.toDomain() = ProductDomain(
    id = id,
    name = title,
    price = price,
)

fun ProductEntity.toDomain(): ProductDomain {
    return ProductDomain(
        id = id,
        name = title,
        price = price,
    )
}


fun Flow<List<ProductEntity>>.toDomain(): Flow<List<ProductDomain>> {
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




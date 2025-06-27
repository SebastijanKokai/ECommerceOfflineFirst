package com.example.ecommercedemo.data.remote.api

import com.example.ecommercedemo.data.remote.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductResponse
}
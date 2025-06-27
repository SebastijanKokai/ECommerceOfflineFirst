package com.example.ecommercedemo.di

import com.example.ecommercedemo.data.repository.ProductRepositoryImpl
import com.example.ecommercedemo.domain.repository.ProductRepository
import com.example.ecommercedemo.domain.usecase.GetProductDetailUseCase
import com.example.ecommercedemo.domain.usecase.GetProductListUseCase
import com.example.ecommercedemo.ui.productdetail.ProductDetailViewModel
import com.example.ecommercedemo.ui.productlist.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }

    factory { GetProductListUseCase(get()) }
    factory { GetProductDetailUseCase(get()) }

    viewModel { ProductListViewModel(get()) }
    viewModel { (productId: Int) -> ProductDetailViewModel(productId, get()) }
}
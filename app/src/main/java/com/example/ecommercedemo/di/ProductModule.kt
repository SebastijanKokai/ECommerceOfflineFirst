package com.example.ecommercedemo.di

import com.example.ecommercedemo.data.repository.ProductRepositoryImpl
import com.example.ecommercedemo.domain.repository.ProductRepository
import com.example.ecommercedemo.domain.usecase.product.GetProductDetailUseCase
import com.example.ecommercedemo.domain.usecase.product.GetProductListUseCase
import com.example.ecommercedemo.domain.usecase.product.RefreshProductsUseCase
import com.example.ecommercedemo.ui.productdetail.ProductDetailViewModel
import com.example.ecommercedemo.ui.productlist.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl(
            dispatcherProvider = get(),
            productApi = get(),
            productDao = get()
        )
    }

    factory { GetProductListUseCase(get()) }
    factory { GetProductDetailUseCase(get()) }
    factory { RefreshProductsUseCase(get()) }

    viewModel {
        ProductListViewModel(
            getProductListUseCase = get(),
            refreshProductsUseCase = get()
        )
    }
    viewModel { (productId: Int) -> ProductDetailViewModel(productId, get()) }
}
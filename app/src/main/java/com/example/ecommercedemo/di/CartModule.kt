package com.example.ecommercedemo.di

import com.example.ecommercedemo.data.repository.CartRepositoryImpl
import com.example.ecommercedemo.domain.repository.CartRepository
import com.example.ecommercedemo.domain.usecase.cart.GetCartItemsUseCase
import com.example.ecommercedemo.domain.usecase.cart.InsertProductToCartUseCase
import com.example.ecommercedemo.ui.cart.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartModule = module {
    single<CartRepository> { CartRepositoryImpl(cartDao = get()) }

    factory { GetCartItemsUseCase(get()) }
    factory { InsertProductToCartUseCase(get()) }

    viewModel {
        CartViewModel(
            getCartItemsUseCase = get(),
            createCartUseCase = get(),
        )
    }
}
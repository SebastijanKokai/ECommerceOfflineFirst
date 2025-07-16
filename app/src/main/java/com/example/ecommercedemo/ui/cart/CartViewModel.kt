package com.example.ecommercedemo.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.core.extension.runCatchingCancellable
import com.example.ecommercedemo.domain.usecase.cart.ClearCartUseCase
import com.example.ecommercedemo.domain.usecase.cart.GetCartItemsUseCase
import com.example.ecommercedemo.domain.usecase.cart.InsertProductToCartUseCase
import com.example.ecommercedemo.domain.usecase.cart.RemoveCartItemUseCase
import com.example.ecommercedemo.ui.cart.model.CartProductUi
import com.example.ecommercedemo.ui.mapper.toUiModel
import com.example.ecommercedemo.ui.shared.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val createCartUseCase: InsertProductToCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val removeCartItemUseCase: RemoveCartItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<CartProductUi>>>(UiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun loadCartItems() {
        viewModelScope.launch {
            getCartItemsUseCase.execute(Unit)
                .map {
                    it.toUiModel()
                }.onStart {
                    _uiState.value = UiState.Loading
                }.catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    if (it.isEmpty()) {
                        _uiState.value = UiState.Empty
                    } else {
                        _uiState.value = UiState.Success(it)
                    }
                }
        }
    }

    fun insertProductToCart(productId: Int, quantity: Int) {
        viewModelScope.launch {
            runCatchingCancellable {
                createCartUseCase.execute(Pair(productId, quantity))
            }.onFailure {
                // @TODO Handle error
            }
        }
    }

    fun removeCartItem(productId: Int) {
        viewModelScope.launch {
            runCatchingCancellable {
                removeCartItemUseCase.execute(productId)
            }.onFailure {
                // @TODO Handle error
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            runCatchingCancellable {
                clearCartUseCase.execute(Unit)
            }.onFailure {
                // @TODO Handle error
            }
        }
    }
}
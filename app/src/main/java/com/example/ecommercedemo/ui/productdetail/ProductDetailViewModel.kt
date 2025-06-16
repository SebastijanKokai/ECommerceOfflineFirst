package com.example.ecommercedemo.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercedemo.domain.repository.ProductRepository
import com.example.ecommercedemo.ui.mapper.toUiModel
import com.example.ecommercedemo.ui.model.ProductUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productId: String,
    private val repository: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<ProductUi?>(null)
    val product: StateFlow<ProductUi?> = _product

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            val product = repository.getProductById(productId)
            _product.value = product?.toUiModel()
        }
    }
}

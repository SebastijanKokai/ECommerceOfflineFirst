@file:Suppress("IllegalIdentifier")

package com.example.ecommercedemo.ui.productlist

import app.cash.turbine.test
import com.example.ecommercedemo.dispatcher.TestDispatcherProvider
import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.domain.usecase.product.GetProductListUseCase
import com.example.ecommercedemo.domain.usecase.product.RefreshProductsUseCase
import com.example.ecommercedemo.ui.shared.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {
    private val testDispatcher = TestDispatcherProvider()

    @MockK
    private lateinit var getProductListUseCase: GetProductListUseCase

    @MockK
    private lateinit var refreshProductsUseCase: RefreshProductsUseCase

    private lateinit var sut: ProductListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher.main)
        sut =
            ProductListViewModel(getProductListUseCase, refreshProductsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit loading then success when use case returns data`() = runTest {
        val mockProducts = listOf(Product(1, "Test product", 10.0, "", "", ""))
        coEvery { getProductListUseCase.execute(Unit) } returns flowOf(mockProducts)

        sut.uiState.test {
            sut.loadProducts()

            assert(awaitItem() is UiState.Initial)
            assert(awaitItem() is UiState.Loading)
            assert(awaitItem() is UiState.Success)
        }
    }
}
package com.example.ecommercedemo.ui.productdetail

import app.cash.turbine.test
import com.example.ecommercedemo.dispatcher.TestDispatcherProvider
import com.example.ecommercedemo.domain.model.Product
import com.example.ecommercedemo.domain.usecase.product.GetProductDetailUseCase
import com.example.ecommercedemo.ui.mapper.toProductDetailUiModel
import com.example.ecommercedemo.ui.shared.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailViewModelTest {
    private lateinit var sut: ProductDetailViewModel
    private lateinit var testDispatcher: TestDispatcherProvider
    private var productId: Int = 1

    @MockK
    private lateinit var mockGetProductDetailUseCase: GetProductDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = TestDispatcherProvider()
        Dispatchers.setMain(testDispatcher.main)
        sut = ProductDetailViewModel(productId, mockGetProductDetailUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit loading then success when use case returns a valid product`() = runTest {
        val mockedProduct = Product(
            id = 1,
            title = "Mocked Product",
            price = 10.0,
            description = "A mocked product for testing",
            category = "mock",
            image = ""
        )

        coEvery { mockGetProductDetailUseCase.execute(productId) } returns mockedProduct

        sut.uiState.test {
            sut.loadProduct()

            assert(awaitItem() is UiState.Initial)
            assert(awaitItem() is UiState.Loading)
            val successState = awaitItem() as UiState.Success
            assert(successState.data == mockedProduct.toProductDetailUiModel())

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should emit loading then error when use case throws an exception`() = runTest {
        coEvery { mockGetProductDetailUseCase.execute(productId) } throws Exception()

        sut.uiState.test {
            sut.loadProduct()

            assert(awaitItem() is UiState.Initial)
            assert(awaitItem() is UiState.Loading)
            assert(awaitItem() is UiState.Error)

            cancelAndConsumeRemainingEvents()
        }
    }
}
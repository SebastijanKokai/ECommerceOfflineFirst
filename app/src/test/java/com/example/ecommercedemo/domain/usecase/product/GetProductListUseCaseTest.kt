package com.example.ecommercedemo.domain.usecase.product

import app.cash.turbine.test
import com.example.ecommercedemo.domain.repository.ProductRepository
import com.example.ecommercedemo.utils.getMockedProducts
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetProductListUseCaseTest {
    private lateinit var sut: GetProductListUseCase

    @MockK
    private lateinit var mockedProductRepository: ProductRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = GetProductListUseCase(mockedProductRepository)
    }

    @Test
    fun `should return a valid list when repository returns a valid list`() = runTest {
        val mockedProducts = getMockedProducts()
        every { mockedProductRepository.getProducts() } returns flowOf(mockedProducts)

        sut.execute(Unit).test {
            assert(awaitItem() == mockedProducts)
            awaitComplete()
        }
    }

    @Test
    fun `should throw an exception when repository throws an exception`() = runTest {
        every { mockedProductRepository.getProducts() } returns flow { throw Exception() }

        sut.execute(Unit).test {
            val error = awaitError()

            assert(error is Exception)
            cancelAndConsumeRemainingEvents()
        }
    }


}
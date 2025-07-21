package com.example.ecommercedemo.ui.productlist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.ecommercedemo.core.testing.TestTags
import com.example.ecommercedemo.ui.productlist.model.ProductListUi
import org.junit.Rule
import org.junit.Test

class ProductListScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    fun getMockedProducts(): List<ProductListUi> {
        return listOf(
            ProductListUi(id = 1, name = "Item A", price = "10.0", image = ""),
            ProductListUi(id = 2, name = "Item B", price = "20.0", image = "")
        )
    }

    @Test
    fun displaysAllProducts_onPhone() {
        val products = getMockedProducts()

        composeTestRule.setContent {
            PhoneProductList(products = products, onClick = {})
        }

        composeTestRule.onNodeWithTag("${TestTags.PRODUCT_ITEM}${products[0].id}").assertExists()
        composeTestRule.onNodeWithTag("${TestTags.PRODUCT_ITEM}${products[1].id}").assertExists()
    }

    @Test
    fun displaysAllProducts_onTablet() {
        val products = getMockedProducts()

        composeTestRule.setContent {
            TabletProductList(products = products, onClick = {})
        }

        composeTestRule.onNodeWithTag("${TestTags.PRODUCT_ITEM}${products[0].id}").assertExists()
        composeTestRule.onNodeWithTag("${TestTags.PRODUCT_ITEM}${products[1].id}").assertExists()
    }
}
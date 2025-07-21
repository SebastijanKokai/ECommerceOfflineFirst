package com.example.ecommercedemo.ui.productlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.testing.TestTags
import com.example.ecommercedemo.ui.productlist.model.ProductListUi
import com.example.ecommercedemo.ui.shared.UiState
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductListViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.start()
    }

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.Initial -> {
            LoadingState()
        }

        UiState.Loading -> {
            LoadingState()
        }

        UiState.Empty -> {
            EmptyState()
        }

        is UiState.Error -> {
            val message = (uiState as UiState.Error).message
            ErrorState(message)
        }

        is UiState.Success<List<ProductListUi>> -> {
            val products = (uiState as UiState.Success<List<ProductListUi>>).data
            ProductListContent(products, onClick = { id ->
                navController.navigate(AppRoute.ProductDetail.createRoute(id))
            })
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ErrorState(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Error: $message", Modifier.align(Alignment.Center))
    }
}

@Composable
private fun EmptyState() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("No products available", Modifier.align(Alignment.Center))
    }
}

@Composable
fun ProductListContent(products: List<ProductListUi>, onClick: (Int) -> Unit) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val isTablet = this.maxWidth > 600.dp

        if (isTablet) {
            TabletProductList(products, onClick)
        } else {
            PhoneProductList(products, onClick)
        }
    }
}

@Composable
fun PhoneProductList(products: List<ProductListUi>, onClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .testTag(TestTags.PHONE_PRODUCT_LIST)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products.count()) { index ->
            val product = products[index]
            ProductItem(
                product = product
            ) {
                onClick(product.id)
            }
        }
    }
}

@Composable
fun TabletProductList(products: List<ProductListUi>, onClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        modifier = Modifier
            .testTag(TestTags.TABLET_PRODUCT_LIST)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products.count()) { index ->
            val product = products[index]
            ProductItem(
                product = product,
                onClick = {
                    onClick(product.id)
                },
            )
        }
    }
}


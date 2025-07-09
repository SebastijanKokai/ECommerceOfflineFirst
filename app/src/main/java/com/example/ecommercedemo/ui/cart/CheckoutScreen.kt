package com.example.ecommercedemo.ui.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommercedemo.core.navigation.AppRoute
import com.example.ecommercedemo.core.navigation.LocalRootNavController
import com.example.ecommercedemo.ui.model.CartProductUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
) {
    val navController = LocalRootNavController.current

    val mockItems = listOf(
        CartProductUi("https://via.placeholder.com/150", "Milk", 2.99, 2, 5.98),
        CartProductUi("https://via.placeholder.com/150", "Bread", 1.49, 1, 1.49)
    )

    var selectedPaymentMethod: String by remember { mutableStateOf("Card") }
    var address by remember { mutableStateOf("123 Main St, Springfield") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Checkout") })
        },
        bottomBar = {
            Button(
                onClick = {
                    navController.navigate(AppRoute.Delivery.path)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsPadding()
            ) {
                Text("Confirm & Pay")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))

            CartItemsSection(mockItems)
            Spacer(Modifier.height(24.dp))

            PaymentMethodsSection(
                selected = selectedPaymentMethod,
                onSelect = { selectedPaymentMethod = it }
            )
            Spacer(Modifier.height(24.dp))

            ShippingAddressSection(
                address = address,
                onAddressChange = { address = it }
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun CartItemsSection(items: List<CartProductUi>) {
    Text("Order Summary", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(8.dp))
    items.forEach { item ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(item.name, style = MaterialTheme.typography.bodyLarge)
                    Text("Quantity: ${item.quantity}")
                    Text("Total: $${item.totalPrice}")
                }
            }
        }
    }
}

@Composable
fun PaymentMethodsSection(
    selected: String,
    onSelect: (String) -> Unit
) {
    Text("Payment Method", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(8.dp))

    val paymentMethods = listOf("Card", "PayPal", "Cash")
    paymentMethods.forEach { method ->
        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (method == selected),
                    onClick = { onSelect(method) }
                )
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = method == selected,
                onClick = { onSelect(method) }
            )
            Spacer(Modifier.width(8.dp))
            Text(method)
        }
    }
}

@Composable
fun ShippingAddressSection(
    address: String,
    onAddressChange: (String) -> Unit
) {
    Text("Shipping Address", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(8.dp))
    OutlinedTextField(
        value = address,
        onValueChange = onAddressChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Enter your address") },
        singleLine = false
    )
}




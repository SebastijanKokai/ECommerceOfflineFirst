package com.example.ecommercedemo.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuantityPicker(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Quantity:", style = MaterialTheme.typography.bodyLarge)

            IconButton(onClick = { onDecrease() }) {
                Icon(Icons.Outlined.Remove, contentDescription = "Decrease quantity")
            }

            Text(quantity.toString(), style = MaterialTheme.typography.bodyLarge)

            IconButton(onClick = { onIncrease() }) {
                Icon(Icons.Outlined.Add, contentDescription = "Increase quantity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuantityPickerPreview() {
    var quantity by remember { mutableStateOf(1) }

    MaterialTheme {
        QuantityPicker(
            quantity = quantity,
            onIncrease = { quantity++ },
            onDecrease = { if (quantity > 1) quantity-- },
        )
    }
}
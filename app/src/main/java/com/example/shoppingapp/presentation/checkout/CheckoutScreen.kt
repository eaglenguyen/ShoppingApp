package com.example.shoppingapp.presentation.checkout

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.product_info.SharedViewModel

@SuppressLint("DefaultLocale")
@Composable
fun CheckoutScreen(
    onClickToCart: () -> Unit,
    onClickToAddressForm: () -> Unit,
    onClickToSuccess: () -> Unit,
    viewModel: SharedViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val formattedTotalPrice = if (state.totalPrice == null) {
        "0.00"
    } else {
        String.format("%.2f", state.totalPrice)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Back Button & Title
        Row (verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onClickToCart() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Check Out",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Address Section
        Card(
            modifier = Modifier.fillMaxWidth().clickable { onClickToAddressForm() },
            shape = RoundedCornerShape(12.dp),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Place,
                    contentDescription = "Location",
                    tint = Color(0xFF7D4CDB),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "${state.address}, ${state.city}, ${state.state} ${state.zipcode}"
                    )
                    Text(
                        text = state.fullName,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Order Summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        ) {
            val totalQuantity = state.totalQuantity.toString()

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Order Summary", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(8.dp))

                OrderSummaryRow("Items", totalQuantity)
                OrderSummaryRow("Subtotal", "$${formattedTotalPrice}")
                OrderSummaryRow("Delivery Charges", "$8")

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)

                OrderSummaryRow("Total", "$${formattedTotalPrice}", isBold = true)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Payment Method
        Text(text = "Choose payment method", fontWeight = FontWeight.Bold)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "💰 Cash", fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.Check, contentDescription = "Selected", tint = Color(0xFF7D4CDB))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Add new payment method",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Payment", tint = Color.Gray)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Confirm Order Button
        Button(
            onClick = { onClickToSuccess() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7D4CDB)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Confirm Order", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun OrderSummaryRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
        Text(text = value, fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
    }
}

package com.example.shoppingapp.presentation.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.navigation.DetailScreen
import com.example.shoppingapp.presentation.product_info.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    onBackClick: () -> Unit,
    onClickToDetails: (Int) -> Unit,
    viewModel: SharedViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        TopAppBar(title = { Text("My Orders") }, navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }, actions = {
            IconButton(onClick = { viewModel.clearOrderList() }) {
                Icon(Icons.Default.Delete, contentDescription = "Menu")
            }
        },
            )
        if (state.orderList.isEmpty()) {
            Text("You currently have no Order History", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(state.orderList) { list ->
                    val orderNum = list.order.orderId.toString()
                    OrderCard(
                        orderNumber = orderNum,
                        trackingNumber = "1Z09123",
                        quantity = 1,
                        totalAmount = "1",
                        date = list.order.orderDate,
                        status = "Delivered",
                        toDetailScreen = { orderId -> onClickToDetails(orderId) }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 5.dp,
                        color = Color.White
                    )
                }
            }
        }


    }
}


@Composable
fun OrderCard(
    orderNumber: String,
    trackingNumber: String,
    quantity: Int,
    totalAmount: String,
    date: String,
    status: String,
    toDetailScreen: (Int) -> Unit
    ) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Order $orderNumber", fontWeight = FontWeight.Bold)
                Text(text = date)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Tracking number: $trackingNumber")
            Spacer(modifier = Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Quantity: $quantity")
                Text(text = "Total Amount: $totalAmount")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = { toDetailScreen(orderNumber.toInt()) }) {
                    Text("Details")
                }
                Text(text = status, color = Color(0xFF27AE60), fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

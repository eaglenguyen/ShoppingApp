package com.example.shoppingapp.presentation.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.shared.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(
    orderId: Int,
    onBackClick: () -> Unit,
    viewModel: SharedViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val selectedOrder = state.orderList.find { it.order.orderId == orderId }

    // val firstOrder = state.orderList.getOrNull(0)




    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Items", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() },

                        ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (state.orderList.isEmpty()) {
                Text("No Order History, Make Your First Purchase!", modifier = Modifier.padding(16.dp))
            } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (selectedOrder != null) {
                            items(selectedOrder.detailItems) { detailItem ->
                                DetailScreen (
                                    item = detailItem,
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
                }
    Spacer(modifier = Modifier.height(16.dp))


}




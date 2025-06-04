package com.example.shoppingapp.presentation.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.product_info.SharedViewModel

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    onCheckoutClick: () -> Unit,
    toOrders: () -> Unit,
    viewModel: SharedViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = {onBackClick()},

                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { toOrders() }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "More")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                if (state.cartList.isEmpty()) {
                    Text("Your cart is currently empty", modifier = Modifier.padding(16.dp))
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        itemsIndexed(state.cartList) { index, item ->
                            // item.price returns total price of items, this returns single unit price
                            val unitPrice = item.price.toDouble() / item.quantity
                            CartItem(
                                item = item,
                                onDelete = { viewModel.removeViaId(index) },
                                removeQuantity = { viewModel.removeQuantityCart(index, unitPrice)
                                                   viewModel.removeItemZero(index)
                                                 },
                                addQuantity = { viewModel.increaseQuantityCart(index, unitPrice) }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                thickness = 5.dp,
                                color = Color.White
                            )

                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))



                // Centered Total Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center // Centers the text horizontally
                ) {
                    val formattedTotalPrice = state.totalPrice?.let { String.format("%.2f", it) } ?: "0.00"
                    Text(
                        text = "Subtotal: $$formattedTotalPrice",
                        fontSize = 18.sp,
                    )
                }

                Button(
                    onClick = onCheckoutClick,
                    enabled =  state.cartList.isNotEmpty(),
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth()
                        .background(Color.Black, shape = RoundedCornerShape(18.dp)),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("Checkout", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

package com.example.shoppingapp.presentation.product_info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.shoppingapp.presentation.shared.SharedViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductInfoScreen(
    id: Int,
    onClickPrevious: () -> Unit,
    onClickToCart: () -> Unit,
    viewModel: SharedViewModel = hiltViewModel(),
) {

    var maxline by rememberSaveable {
        mutableStateOf(false)
    }

    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()




    val state = viewModel.state
    if (state.error == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = { onClickPrevious() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle favorite action */ }) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite"
                            )
                        }
                        IconButton(onClick = { onClickToCart() }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart"
                            )
                        }
                    },

                    )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                state.product?.let { product ->

                    // Image Section
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(Color.White),
                        contentAlignment = Alignment.TopEnd
                    ) {

                        AsyncImage(
                            model = product.image,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Details Section
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // Title and Price
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Rating
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color(0xFFFFD700)
                            )
                            Text(
                                text = "${product.rating.rate} (${product.rating.count} reviews)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                            )

                            Spacer(modifier = Modifier.weight(1f))


                            Text(
                                text = "$${product.price}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Blue
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Description
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = product.description,
                            maxLines =
                            when(maxline) {
                                false -> 10
                                else -> Int.MAX_VALUE
                            },
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.clickable { maxline = !maxline }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Sizes
                        Text(
                            text = "Size",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            val sizes = listOf(
                                "S",
                                "M",
                                "L",
                                "XL"
                            )
                            sizes.forEach { size ->
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                                        .clickable { /* Handle size selection */ },
                                    contentAlignment = Center
                                ) {
                                    Text(text = size)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Buy Now Button
                        Button(
                            onClick = {
                                viewModel.addToCart(product)
                                showBottomSheet = true
                                      },
                            modifier = Modifier.padding(16.dp)
                                .fillMaxWidth()
                                .background(Color.Black, shape = RoundedCornerShape(18.dp)),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(Color.Black),
                        ) {
                            Text(text = "ADD TO CART")
                        }

                        if(showBottomSheet){
                            ModalBottomSheet(
                                onDismissRequest = { showBottomSheet = false },
                                sheetState = sheetState
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row {
                                        Icon(
                                        imageVector = Icons.Default.CheckCircle, // Success check icon
                                        contentDescription = null,
                                        tint = Color(0xFF4CAF50), // Green color for the icon
                                        modifier = Modifier.size(24.dp)
                                    )
                                        Text(
                                            text = "Added to cart",
                                            color = Color(0xFF4CAF50), // Green text color
                                            fontSize = 16.sp,
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        )
                                    }

                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween

                                    ) {
                                        Button(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxWidth().background(Color.Black, shape = RoundedCornerShape(18.dp)),
                                            colors = ButtonColors(
                                                containerColor = Color.Black,
                                                contentColor = Color.White,
                                                disabledContainerColor = Color.Gray,
                                                disabledContentColor = Color.White
                                            ),
                                                        onClick = {
                                                scope.launch {
                                                    sheetState.hide()
                                                }.invokeOnCompletion {
                                                    if(!sheetState.isVisible) {
                                                        showBottomSheet = false
                                                        onClickToCart()
                                                        // homeViewModel.changeItemIndex(2)
                                                    }
                                                }
                                            }
                                        )
                                        {
                                            Text("Cart")
                                        }

                                        Spacer(modifier = Modifier.width(20.dp))

                                        Button(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxWidth()
                                                .border(1.dp, Color.Gray, RoundedCornerShape(18.dp)),
                                            colors = ButtonColors(
                                                containerColor = Color.White,
                                                contentColor = Color.Black,
                                                disabledContainerColor = Color.Gray,
                                                disabledContentColor = Color.White
                                            ),
                                            onClick = {
                                                scope.launch {
                                                    sheetState.hide()
                                                }.invokeOnCompletion {
                                                    if(!sheetState.isVisible) {
                                                        showBottomSheet = false
                                                    }
                                                }
                                            }
                                        )
                                        {
                                            Text("Close")
                                        }
                                    }
                                }

                            }
                        }

                        }
                    }
                }

        }
    }

}
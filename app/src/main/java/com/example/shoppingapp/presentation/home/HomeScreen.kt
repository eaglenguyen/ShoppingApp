package com.example.shoppingapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.PullToRefreshLazyColumn
import com.example.shoppingapp.util.Resource


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickToSignUpScreen: () -> Unit
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.homeResult.collect { result ->
            when(result) {
                is Resource.Success-> {
                    Unit
                }
                is Resource.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You have signed out",
                        Toast.LENGTH_LONG
                    ).show()
                    onClickToSignUpScreen()
                }
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is Resource.Loading -> {
                    Unit
                }
            }
        }
    }



    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = state.selectedItemIndex == index,
                        onClick = { viewModel.changeItemIndex(index) },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == state.selectedItemIndex) {
                                    item.selectedItem
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )

                        }
                    )
                }

            }
        }
    ) { scaffpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffpadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                PullToRefreshLazyColumn(
                    items = state.productList,
                    content = { item ->
                        ProductItem(
                            item.title,
                            "$${item.price}",
                            item.image
                        )
                    },
                    isRefreshing = state.isRefreshing,
                    onRefresh = { viewModel.refreshProducts() },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

}
package com.example.shoppingapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.PullToRefreshLazyColumn
import com.example.shoppingapp.presentation.profile.ProfileScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickToSignUp: () -> Unit
) {

    val state = viewModel.state


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (state.selectedItemIndex == 0) {
                        Text("Products")
                    } else {
                        Text("")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = state.selectedItemIndex == index,
                        onClick = {
                            viewModel.changeItemIndex(index)
                                  },
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
        when(state.selectedItemIndex) {
            0 -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(scaffpadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
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
            3 -> { ProfileScreen(scaffpadding, onClickToSignUp) }
        }

    }


}



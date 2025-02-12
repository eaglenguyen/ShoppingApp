package com.example.shoppingapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.navigation.CartScreen
import com.example.shoppingapp.presentation.PullToRefreshLazyColumn
import com.example.shoppingapp.presentation.cart.CartScreen
import com.example.shoppingapp.presentation.profile.ProfileScreen


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickToSignUp: () -> Unit,
    onClickToSettings: () -> Unit,
    onClickToDetails: (Int) -> Unit
) {


    val state = viewModel.state


    Scaffold(
        topBar = {
            if (state.selectedItemIndex == 1) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.onEvent(HomeScreenUiEvent.OnSearchQueryChange(query = it)) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .testTag("search"),
                    placeholder = { Text(text = "Search...") },
                    maxLines = 1,
                    singleLine = true
                )
            }
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
            0,1 -> {
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
                                    title = item.title,
                                    price = "$${item.price}",
                                    image = item.image,
                                    modifier = Modifier.clickable { onClickToDetails(item.id) }
                                )

                            },
                            isRefreshing = state.isRefreshing,
                            onRefresh = { viewModel.refreshProducts() },
                            modifier = Modifier.fillMaxSize(),
                            )
                    }
                }
            }
            2 -> {
                CartScreen(
                    onBackClick = { Unit },
                    onCheckoutClick = { Unit }
                )
            }
            3 -> { ProfileScreen(scaffpadding, onClickToSignUp, onClickToSettings) }

        }

    }


}



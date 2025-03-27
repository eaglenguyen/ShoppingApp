package com.example.shoppingapp.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.cart.CartScreen
import com.example.shoppingapp.presentation.profile.ProfileScreen
import com.example.shoppingapp.ui.theme.jua


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickToSignUp: () -> Unit,
    onClickToSettings: () -> Unit,
    onClickToDetails: (Int) -> Unit,
    onClickToCheckOut: () -> Unit
) {


    val state = viewModel.state
    val gridState = rememberLazyStaggeredGridState()
    val selectedItemIndex = state.selectedItemIndex



    Scaffold(
        topBar = {
            if (selectedItemIndex == 1) {
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
            if(selectedItemIndex != 2) {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            viewModel.changeItemIndex(index)
                        },
                        label = {
                            // Animation for bottombar text
                            AnimatedVisibility(
                                visible = selectedItemIndex == index,
                                enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
                                exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
                            ) {
                                Text(text = item.title, fontFamily = jua, fontSize = 14.sp)
                            }
                            if (selectedItemIndex != index) {
                                Text(text = "") // Keeps the height consistent
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedItemIndex == index) {
                                    item.selectedItem
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )

                        }
                    )
                }
            }
            }
        }
    ) { scaffpadding ->
        when(selectedItemIndex) {
            0,1 -> {
                if (state.error == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(scaffpadding),
                        contentAlignment = Alignment.Center,
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Fixed(2),
                                state = gridState,
                                verticalItemSpacing = 8.dp,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxSize()
                            )
                            {
                                items(state.productList) { item ->
                                    ProductItem(
                                        title = item.title,
                                        description = item.description,
                                        price = "$${item.price}",
                                        image = item.image,
                                        rating = item.rating.rate.toString(),
                                        modifier = Modifier.clickable { onClickToDetails(item.id) }
                                    )
                                }
                            }

                        }

                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else if (state.error != null) {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            2 -> {
                CartScreen(
                    onBackClick = { viewModel.changeItemIndex(0) },
                    onCheckoutClick = { onClickToCheckOut() }
                )
            }
            3 -> { ProfileScreen(scaffpadding, onClickToSignUp, onClickToSettings) }

        }

    }


}


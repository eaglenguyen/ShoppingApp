package com.example.shoppingapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductsRepository
): ViewModel() {

    private var state by mutableStateOf(HomeState())


    init {
        showProducts()
    }

    private fun showProducts() {
        viewModelScope.launch {
            repository.getProductsList().collect {
                when(it) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                    }
                    is Resource.Success -> {
                        state = state.copy(isLoading = false, productList = it.data!!.products)
                    }
                }
            }
        }
    }

}
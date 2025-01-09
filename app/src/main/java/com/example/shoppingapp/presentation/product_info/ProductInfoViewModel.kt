package com.example.shoppingapp.presentation.product_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val saveStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(ProductInfoState())

    init {
        viewModelScope.launch {
            val position = saveStateHandle.get<Int>("id") ?: return@launch
            state = state.copy(isLoading = true)
            when (val productResult = repository.getProduct(position)) {
                is Resource.Error -> {
                    state = state.copy(
                    isLoading = false,
                    error = productResult.message,
                    product = null
                )
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
                is Resource.Success -> {
                    state = state.copy(
                        product = productResult.data,
                        isLoading = false,
                        error = null
                    )
                }
                else -> Unit

            }

        }
    }


}
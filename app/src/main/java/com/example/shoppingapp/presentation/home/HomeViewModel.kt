package com.example.shoppingapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductsRepository
): ViewModel() {

    var state by mutableStateOf(HomeState())


    private val resultChannel = Channel<Resource<Unit>>()
    val homeResult = resultChannel.receiveAsFlow()


    init {
        showProducts()
    }

    fun refreshProducts() {
        showProducts()
    }

    fun showDialogState() {
        state = state.copy(showDialog = !state.showDialog)
    }

    fun changeItemIndex(index: Int) {
        state = state.copy(selectedItemIndex = index)
    }




    fun onEvent(event: HomeScreenUiEvent) {
        when(event) {
            HomeScreenUiEvent.SignOut -> signOut()
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signOut()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun showProducts() {
        viewModelScope.launch {
            repository.getProductsList().collect {
                when(it) {
                    is Resource.Loading -> {
                        state = state.copy(isRefreshing = true)
                        state = state.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            isRefreshing = false,
                            isLoading = false,
                            productList = it.data!!
                        )
                    }

                    is Resource.Unauthorized -> {
                        state = state.copy(isLoading = false)
                    }
                }
            }
        }
    }

}
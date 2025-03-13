package com.example.shoppingapp.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductsRepository,
): ViewModel() {

    var state by mutableStateOf(HomeState())


    private var searchJob: Job? = null

    private val resultChannel = Channel<Resource<Unit>>()
    // val homeResult = resultChannel.receiveAsFlow()

    init {
        showProducts()
    }





    fun changeItemIndex(index: Int) {
        state = state.copy(selectedItemIndex = index)
    }




    fun onEvent(event: HomeScreenUiEvent) {
        when(event) {
            is HomeScreenUiEvent.SignOut -> signOut()
            is HomeScreenUiEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch() {
                    delay(500L)
                    showProducts()
                }
            }
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

     private fun showProducts(
        query: String = state.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(100)
            repository.getProductsList(query).collectLatest {
                when(it) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            productList = it.data ?: emptyList(),
                            error = null
                        )
                    }
                    is Resource.Error, is Resource.Unauthorized -> {
                        Log.e("HomeViewModel", "Error: ${it.message}")
                        state = state.copy(
                            isLoading = false,
                            error = it.message,
                        )
                    }
                    else -> Unit
                }
            }
        }
    }

}
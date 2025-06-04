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

    // Able to control coroutine
    private var searchJob: Job? = null


    init {
        showProducts()
    }





    fun changeItemIndex(index: Int) {
        state = state.copy(selectedItemIndex = index)
    }



    // Cancels any previous Job before launching a new one to avoid launching a coroutine after every keystroke
    fun onEvent(event: HomeScreenUiEvent) {
        when(event) {
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



     private fun showProducts(
        query: String = state.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getProductsList(query).collect {
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
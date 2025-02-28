package com.example.shoppingapp.presentation.profile

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
class ProfileViewModel @Inject constructor(
    private val repository: ProductsRepository
): ViewModel() {

    var state by mutableStateOf(ProfileState())


    private val resultChannel = Channel<Resource<Unit>>()
    val profileResult = resultChannel.receiveAsFlow()


    fun showDialogState() {
        state = state.copy(showDialog = !state.showDialog)
    }


    fun onEvent(event: ProfileScreenUiEvent) {
        when(event) {
            ProfileScreenUiEvent.SignOut -> signOut()
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


}
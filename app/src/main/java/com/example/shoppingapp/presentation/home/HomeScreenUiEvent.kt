package com.example.shoppingapp.presentation.home


sealed class HomeScreenUiEvent {
    object SignOut : HomeScreenUiEvent()
    data class OnSearchQueryChange(val query: String): HomeScreenUiEvent()

}

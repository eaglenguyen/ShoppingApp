package com.example.shoppingapp.presentation.home


sealed class HomeScreenUiEvent {
    data class OnSearchQueryChange(val query: String): HomeScreenUiEvent()

}

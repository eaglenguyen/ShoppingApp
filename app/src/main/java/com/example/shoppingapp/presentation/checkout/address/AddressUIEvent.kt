package com.example.shoppingapp.presentation.checkout.address

sealed class AddressUIEvent {
    data class NameChange (val value: String): AddressUIEvent()
    data class AddressChange (val value: String): AddressUIEvent()
    data class CityChange (val value: String): AddressUIEvent()
    data class StateChange (val value: String): AddressUIEvent()
    data class ZipCodeChange (val value: String): AddressUIEvent()
    object SaveAddress: AddressUIEvent()

}
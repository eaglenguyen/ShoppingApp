package com.example.shoppingapp.presentation.product_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.remote.address.AddressEntity
import com.example.shoppingapp.data.repository.AddressRepository
import com.example.shoppingapp.data.repository.CartRepository
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.presentation.checkout.address.AddressUIEvent
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val cartRepository: CartRepository,
    private val addressRepository: AddressRepository,
    private val saveStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(ProductInfoState())
        private set


    init {
        loadProduct()
        observeCartPriceQuantity()
        getSavedAddress()
    }

    fun onEvent(event: AddressUIEvent) {
        when(event) {
            is AddressUIEvent.NameChange -> { state = state.copy(fullName = event.value) }
            is AddressUIEvent.AddressChange -> { state = state.copy(address = event.value) }
            is AddressUIEvent.CityChange -> { state = state.copy(city = event.value) }
            AddressUIEvent.SaveAddress -> saveAddress()
            is AddressUIEvent.StateChange -> { state = state.copy(state = event.value) }
            is AddressUIEvent.ZipCodeChange -> { state = state.copy(zipcode = event.value) }
        }
    }

    private fun saveAddress () {
        viewModelScope.launch {
            addressRepository.saveAddress(
                AddressEntity(
                    fullName = state.fullName,
                    address = state.address,
                    city = state.city,
                    state = state.state,
                    zipcode = state.zipcode
                )
            )
        }
    }

    // collect {} to listen for updates and update the UI state whenever a new address is emitted.
    private fun getSavedAddress() {
        viewModelScope.launch {
            addressRepository.getSavedAddress().collect { address ->
                address?.let {
                    state = state.copy(
                        fullName = it.fullName,
                        address = it.address,
                        city = it.city,
                        state = it.state,
                        zipcode = it.zipcode
                    )
                }
            }
        }
    }



    // Combines 3 flows and collect them into one function
    private fun observeCartPriceQuantity() {
        viewModelScope.launch {
            combine(
                cartRepository.getCart(),
                cartRepository.getTotalPrice(),
                cartRepository.getTotalQuantity()
            ) { cartItems, cartPrice, quantity ->
                state.copy(cartList = cartItems, totalPrice = cartPrice, totalQuantity = quantity)
            }.collectLatest { newState ->
                state = newState
            }
        }
    }





/*    private fun loadCartItems() {
        viewModelScope.launch {
            cartRepository.getCart().collect { cartItems ->
                state = state.copy(cartList = cartItems)
            }
        }
    }
*/



    private fun loadProduct() {
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

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addToCart(product)
        }
    }



    fun removeViaId(position: Int) {
        viewModelScope.launch {
            cartRepository.removeViaId(position)
        }
    }

    fun increaseQuantityCart(position: Int, itemPrice: Double) {
        viewModelScope.launch {
            cartRepository.increaseQuantityCart(position, itemPrice)
        }
    }

    fun removeQuantityCart(position: Int, itemPrice: Double) {
        viewModelScope.launch {
            cartRepository.removeQuantityCart(position, itemPrice)
        }
    }

    fun removeItemZero(position: Int) {
        viewModelScope.launch {
            cartRepository.removeIfZero(position)
        }
    }



}
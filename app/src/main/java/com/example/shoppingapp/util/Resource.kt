package com.example.shoppingapp.util



sealed class Resource <T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data,null)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Unauthorized<T>: Resource<T>()
}

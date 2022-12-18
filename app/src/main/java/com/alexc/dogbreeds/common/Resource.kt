package com.alexc.dogbreeds.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T, val fromCache: Boolean = false) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}

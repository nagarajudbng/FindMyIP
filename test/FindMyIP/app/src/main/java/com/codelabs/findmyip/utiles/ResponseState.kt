package com.codelabs.findmyip.utiles

sealed class ResponseState<T> {
    class Loading<T> : ResponseState<T>()
    data class Success<T>(val data:T) : ResponseState<T>()
    data class Fail<T>(val error:String) : ResponseState<T>()
}
package com.apparel.offprice.common.utils

sealed class ResponseState<out R> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error<out E>(val exception: Exception) : ResponseState<E>()
    data class Loading<out B>(val loading: Boolean) : ResponseState<B>()
}
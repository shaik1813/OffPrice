package com.apparel.offprice.common.utils

sealed class UiError {
    object Network : UiError()
    object Timeout : UiError()
    data class Server(val code: Int) : UiError()
}
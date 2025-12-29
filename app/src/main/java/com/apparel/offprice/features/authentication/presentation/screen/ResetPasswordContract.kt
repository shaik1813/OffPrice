package com.apparel.offprice.features.authentication.presentation.screen


import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.routes.AppScreen

interface ResetPasswordContract : UnidirectionalViewModel
<ResetPasswordContract.UiState, ResetPasswordContract.UiEvent, ResetPasswordContract.UiEffect> {

    data class UiState(
        val passwordValue: String = "",
        val repasswordValue: String = "",
        val showPassword: Boolean = false,
        val reshowPassword: Boolean = false,

        )

    sealed interface UiEvent {
        object OnNavigateBack : UiEvent
        object OnPasswordVisibleToggle : UiEvent
        object OnRePasswordVisibleToggle : UiEvent
        data class OnValueChangePassword(val value: String) : UiEvent
        data class OnValueChangeRePassword(val value: String) : UiEvent

    }

    sealed interface UiEffect {
        data class Navigate(val screen: AppScreen) : UiEffect
        data object OnNavigateBack : UiEffect
    }
}
package com.apparel.offprice.features.authentication.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.routes.AppScreen

interface LoginContract : UnidirectionalViewModel
<LoginContract.UiState, LoginContract.UiEvent, LoginContract.UiEffect> {

    data class UiState(
        val isLoginVisible: Boolean = false,
        val isRememberCheck: Boolean = false,
        val passwordValue: String = "",
        val email: String = "",
        val showPassword: Boolean = false,
    )

    sealed interface UiEvent {
        object OnLoginClick : UiEvent
        object OnCloseLogin : UiEvent
        object OnNavigateBack : UiEvent
        object OnCheckToggle : UiEvent
        object OnPasswordVisibleToggle : UiEvent
        data class OnValueChangeEmail(val value: String) : UiEvent
        data class OnValueChangePassword(val value: String) : UiEvent
        data class OnNavigate(val screen: AppScreen) : UiEvent
    }

    sealed interface UiEffect {
        data class Navigate(val screen: AppScreen) : UiEffect
        data object OnNavigateBack : UiEffect

    }
}
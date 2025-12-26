package com.apparel.offprice.features.authentication.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.routes.AppScreen

interface LoginContract : UnidirectionalViewModel
<LoginContract.UiState, LoginContract.UiEvent, LoginContract.UiEffect> {

    data class UiState(
        val isLoginVisible: Boolean = false,
        val isRememberCheck: Boolean = false,
        val passwordValue: String = "",
        val email: String = "",
        val name: String = "",
        val phoneNumber: String = "",
        val showPassword: Boolean = false,
        val playEnterAnimation: Boolean = true,
        val isLoginScreen: Boolean = true,
        val isForgotScreen: Boolean = true,
        val isSignUpScreen: Boolean = false,
        val isCountryPickerOpen: Boolean = false,
        val phoneCode: Country = countryList.first(),
        val nameError: String? = null,
        val emailError: String? = null,
        val phoneError: String? = null,
    )

    sealed interface UiEvent {
        object OnLoginClick : UiEvent
        object OnCloseLogin : UiEvent
        object OnNavigateBack : UiEvent
        object OnCheckToggle : UiEvent
        object OnAnimationToggle : UiEvent

        object OnPasswordVisibleToggle : UiEvent
        object OnOpenSignUp : UiEvent
        object OnOpenLogin : UiEvent
        object OnOpenForgot : UiEvent

        data class OnPhoneChange(val value: String) : UiEvent
        data class SelectCountry(val country: Country) : UiEvent

        data class OnValueChangeName(val value: String) : UiEvent

        data class OnValueChangeEmail(val value: String) : UiEvent
        data class OnValueChangePassword(val value: String) : UiEvent
        data class OnNavigate(val screen: AppScreen) : UiEvent

    }

    sealed interface UiEffect {
        data class Navigate(val screen: AppScreen) : UiEffect
        data object OnNavigateBack : UiEffect
    }
}
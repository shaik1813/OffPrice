package com.apparel.offprice.features.authentication.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.authentication.data.AuthPage
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.routes.AppScreen

interface LoginContract : UnidirectionalViewModel
<LoginContract.UiState, LoginContract.UiEvent, LoginContract.UiEffect> {

    data class UiState(
        val currentPage: AuthPage = AuthPage.LOGIN,
        val isRememberCheck: Boolean = false,
        val passwordValue: String = "",
        val email: String = "",
        val name: String = "",
        val phoneNumber: String = "",
        val showPassword: Boolean = false,
        val playEnterAnimation: Boolean = true,
        val isLoginScreen: Boolean = true,
        val isForgotScreen: Boolean = false,
        val isSignUpScreen: Boolean = false,
        val isResetPasswordScreen: Boolean = false,
        val isCountryPickerOpen: Boolean = false,
        val phoneCode: Country = countryList.first(),
        val nameError: String? = null,
        val emailError: String? = null,
        val phoneError: String? = null,
    )

    sealed interface UiEvent {
        object OnCloseLogin : UiEvent
        object OnNavigateBack : UiEvent
        object OnBackClick: UiEvent
        object OnCheckToggle : UiEvent
        object OnAnimationToggle : UiEvent

        object OnLoginClick : UiEvent

        object OnPasswordVisibleToggle : UiEvent
        object OnOpenSignUp : UiEvent
        object OnOpenLogin : UiEvent
        object OnOpenForgot : UiEvent
        object OnOpenResetPassword : UiEvent

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
        data object NavigateToHomeScreen : UiEffect
    }
}
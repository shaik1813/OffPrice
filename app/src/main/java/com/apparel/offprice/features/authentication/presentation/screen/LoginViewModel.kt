package com.apparel.offprice.features.authentication.presentation.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.authentication.data.AuthPage
import com.apparel.offprice.features.authentication.presentation.screen.LoginContract.UiEffect.Navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel(), LoginContract {

    val _state = MutableStateFlow(LoginContract.UiState())
    override val state: StateFlow<LoginContract.UiState> = _state.asStateFlow()

    val _effect = MutableSharedFlow<LoginContract.UiEffect>()
    override val effect: SharedFlow<LoginContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: LoginContract.UiEvent) {
        when (event) {
            LoginContract.UiEvent.OnCloseLogin -> {
                viewModelScope.launch {
                    _effect.emit(LoginContract.UiEffect.OnNavigateBack)
                }
            }

            is LoginContract.UiEvent.OnNavigate -> {
                viewModelScope.launch {
                    _effect.emit(
                        Navigate(event.screen)
                    )
                }
            }

            LoginContract.UiEvent.OnBackClick -> {
                viewModelScope.launch {
                    updateState { it.copy(currentPage = AuthPage.LOGIN, isLoginScreen = true, isForgotScreen = false,
                        isResetPasswordScreen = false) }
                }
            }

            is LoginContract.UiEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    _effect.emit(LoginContract.UiEffect.OnNavigateBack)
                }
            }

            LoginContract.UiEvent.OnCheckToggle -> {
                updateState { it.copy(isRememberCheck = !it.isRememberCheck) }
            }

            is LoginContract.UiEvent.OnValueChangeEmail -> {
                updateState { it.copy(email = event.value) }
            }

            is LoginContract.UiEvent.OnValueChangePassword -> {
                updateState { it.copy(passwordValue = event.value) }
            }

            LoginContract.UiEvent.OnPasswordVisibleToggle -> {
                updateState { it.copy(showPassword = !it.showPassword) }
            }

            LoginContract.UiEvent.OnAnimationToggle -> {
                if (state.value.playEnterAnimation) {
                    updateState { it.copy(playEnterAnimation = false) }
                }
            }

            LoginContract.UiEvent.OnOpenSignUp -> {
                updateState { it.copy(isLoginScreen = false, isSignUpScreen = true, currentPage = AuthPage.SIGNUP) }
            }

            LoginContract.UiEvent.OnOpenLogin -> {
                updateState { it.copy(isLoginScreen = true) }
            }

            is LoginContract.UiEvent.OnValueChangeName -> {
                updateState { it.copy(name = event.value) }
            }

            is LoginContract.UiEvent.OnPhoneChange -> {
                updateState {
                    it.copy(
                        phoneNumber = event.value,
                        phoneError = if (!event.value.matches(Regex("\\d+"))) "Digits only" else null
                    )
                }
            }

            is LoginContract.UiEvent.SelectCountry -> {
                updateState {
                    it.copy(phoneCode = event.country, isCountryPickerOpen = false)
                }
            }

            LoginContract.UiEvent.OnOpenForgot -> {
                updateState {
                    it.copy(
                        isForgotScreen = true,
                        isLoginScreen = false,
                        isSignUpScreen = false,
                        currentPage = AuthPage.FORGOT_PASSWORD
                    )
                }
            }

            LoginContract.UiEvent.OnOpenResetPassword -> {
                updateState { it.copy(isResetPasswordScreen = true, isForgotScreen = false, isLoginScreen = false,
                    currentPage = AuthPage.RESET_PASSWORD) }
            }
        }
    }

    private fun updateState(reducer: (LoginContract.UiState) -> LoginContract.UiState) {
        _state.value = reducer(_state.value)
    }
}
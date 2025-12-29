package com.apparel.offprice.features.authentication.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SignUpViewModel @Inject constructor(
) : ViewModel(), SignUpContract {

    val _state = MutableStateFlow(SignUpContract.UiState())
    override val state: StateFlow<SignUpContract.UiState> = _state.asStateFlow()

    val _effect = MutableSharedFlow<SignUpContract.UiEffect>()
    override val effect: SharedFlow<SignUpContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: SignUpContract.UiEvent) {
        when (event) {
            SignUpContract.UiEvent.OnCloseLogin -> {
                updateState {
                    it.copy(isLoginVisible = false)
                }
                viewModelScope.launch {
                    _effect.emit(SignUpContract.UiEffect.OnNavigateBack)
                }
            }

            SignUpContract.UiEvent.OnLoginClick -> {
                updateState { it.copy(isLoginVisible = true) }
            }

            is SignUpContract.UiEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    _effect.emit(SignUpContract.UiEffect.OnNavigateBack)
                }
            }

            SignUpContract.UiEvent.OnCheckToggle -> {
            }

            is SignUpContract.UiEvent.OnValueChangeEmail -> {
                updateState { it.copy(email = event.value) }
            }

            is SignUpContract.UiEvent.OnValueChangePassword -> {
                updateState { it.copy(passwordValue = event.value) }
            }

            SignUpContract.UiEvent.OnPasswordVisibleToggle -> {
                updateState { it.copy(showPassword = !it.showPassword) }
            }

            is SignUpContract.UiEvent.OnNavigate -> {

            }

            is SignUpContract.UiEvent.OnValueChangeName -> {
                updateState { it.copy(name = event.value) }
            }

            SignUpContract.UiEvent.OnOpenLogin -> {
                updateState { it.copy(isLoginScreen = true) }
            }

            is SignUpContract.UiEvent.OnPhoneChange -> {
                updateState {
                    it.copy(
                        phoneNumber = event.value,
                        phoneError = if (!event.value.matches(Regex("\\d+"))) "Digits only" else null
                    )
                }
            }
            is SignUpContract.UiEvent.SelectCountry -> {
                updateState {
                    it.copy(phoneCode = event.country, isCountryPickerOpen = false)
                }
            }

            SignUpContract.UiEvent.OnOpenOtp -> {
                updateState { it.copy(showOtpDialog = true) }
            }

            SignUpContract.UiEvent.OnCloseOtp -> {
                updateState { it.copy(showOtpDialog = false) }
            }
        }
    }

    private fun updateState(reducer: (SignUpContract.UiState) -> SignUpContract.UiState) {
        _state.value = reducer(_state.value)
    }
}
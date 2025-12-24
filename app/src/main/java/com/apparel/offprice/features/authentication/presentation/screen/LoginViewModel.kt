package com.apparel.offprice.features.authentication.presentation.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                updateState { it.copy(isLoginVisible = false)
                }
                viewModelScope.launch {
                   // delay(300) // match your bottom sheet animation duration
                    _effect.emit(LoginContract.UiEffect.OnNavigateBack)
                }
            }

            LoginContract.UiEvent.OnLoginClick -> {
                updateState { it.copy(isLoginVisible = true) }
            }

            is LoginContract.UiEvent.OnNavigate -> {
                viewModelScope.launch {
                    _effect.emit(
                        Navigate(event.screen)
                    )
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


        }
    }

    private fun updateState(reducer: (LoginContract.UiState) -> LoginContract.UiState) {
        _state.value = reducer(_state.value)
    }
}
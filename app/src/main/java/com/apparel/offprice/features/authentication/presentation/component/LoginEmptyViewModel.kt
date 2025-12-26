package com.apparel.offprice.features.authentication.presentation.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
/*

class LoginEmptyViewModel  @Inject constructor(
) : ViewModel(), LoginEmptyContract {


    val _state = MutableStateFlow(LoginEmptyContract.EmptyUiState())
    override val state: StateFlow<LoginEmptyContract.EmptyUiState> = _state.asStateFlow()

    val _effect = MutableSharedFlow<LoginEmptyContract.EmptyUiEffect>()
    override val effect: SharedFlow<LoginEmptyContract.EmptyUiEffect> = _effect.asSharedFlow()

    override fun event(event: LoginEmptyContract.EmptyUiEvent) {
        when (event) {
            LoginEmptyContract.EmptyUiEvent.OnCloseLogin -> {
                updateState {
                    it.copy(isLoginVisible = false)
                }
                viewModelScope.launch {
                    _effect.emit(LoginEmptyContract.UiEffect.OnNavigateBack)
                }
            }
            LoginEmptyContract.EmptyUiEvent.OnLoginClick -> {
                updateState { it.copy(isLoginVisible = true) }
            }

            LoginEmptyContract.EmptyUiEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    _effect.emit(LoginEmptyContract.EmptyUiEffect.OnNavigateBack)
                }
            }

            is LoginEmptyContract.EmptyUiEvent.OnNavigate -> {
                viewModelScope.launch {
                    _effect.emit(LoginEmptyContract.EmptyUiEffect.Navigate2)
                }
            }
        }

    }
    private fun updateState(reducer: (LoginEmptyContract.EmptyUiState) -> LoginEmptyContract.EmptyUiState) {
        _state.value = reducer(_state.value)
    }
}*/

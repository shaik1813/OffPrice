package com.apparel.offprice.features.profile.presentation.screen.profilePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.profile.presentation.screen.profilePassword.ProfilePasswordContract.UiEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePasswordViewModel @Inject constructor() : ViewModel(), ProfilePasswordContract {

    private val _state = MutableStateFlow(ProfilePasswordContract.UiState())
    override val state: StateFlow<ProfilePasswordContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<ProfilePasswordContract.UiEffect>()
    override val effect: SharedFlow<ProfilePasswordContract.UiEffect> = _effectFlow.asSharedFlow()


    override fun event(event: ProfilePasswordContract.UiEvent) {
        when (event) {
            is ProfilePasswordContract.UiEvent.OnNewPasswordChanged -> {
                _state.update { it.copy(newPassword = event.newPass) }
            }

            is ProfilePasswordContract.UiEvent.OnConfirmPasswordChanged -> {
                _state.update { it.copy(confirmPassword = event.confirmPass) }
            }

            ProfilePasswordContract.UiEvent.OnChangePasswordClicked -> {
                viewModelScope.launch {
                    if (_state.value.newPassword.isEmpty()){
                        _effectFlow.emit(ShowError("New password cannot be empty"))
                    }else if (_state.value.confirmPassword.isEmpty()){
                        _effectFlow.emit(ShowError("Confirm password cannot be empty"))
                    }else if (_state.value.newPassword != _state.value.confirmPassword) {
                        _effectFlow.emit(ShowError("Passwords do not match"))
                    } else {
                        _effectFlow.emit(ShowPasswordChangedSuccess)
                    }
                }
            }

            ProfilePasswordContract.UiEvent.ToggleConfirmPasswordVisibility -> {
                _state.update { it.copy(isConfirmPasswordVisible = !_state.value.isConfirmPasswordVisible) }
            }

            ProfilePasswordContract.UiEvent.ToggleNewPasswordVisibility -> {
                _state.update { it.copy(isNewPasswordVisible = !_state.value.isNewPasswordVisible) }
            }

            ProfilePasswordContract.UiEvent.OnCancelChangePasswordClicked -> {
                viewModelScope.launch {
                    _effectFlow.emit(NavigateBack)
                }
            }
        }
    }
}
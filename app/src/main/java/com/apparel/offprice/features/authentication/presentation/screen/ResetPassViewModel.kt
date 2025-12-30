package com.apparel.offprice.features.authentication.presentation.screen





import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ResetPassViewModel @Inject constructor(
) : ViewModel(), ResetPasswordContract {
    val _state = MutableStateFlow(ResetPasswordContract.UiState())
    override val state: StateFlow<ResetPasswordContract.UiState> = _state.asStateFlow()

    val _effect = MutableSharedFlow<ResetPasswordContract.UiEffect>()
    override val effect: SharedFlow<ResetPasswordContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: ResetPasswordContract.UiEvent) {
        when(event){
            ResetPasswordContract.UiEvent.OnNavigateBack -> {}
            is ResetPasswordContract.UiEvent.OnValueChangePassword -> {
                updateState { it.copy(passwordValue = event.value) }
            }
            is ResetPasswordContract.UiEvent.OnValueChangeRePassword -> {
                updateState { it.copy(repasswordValue = event.value) }
            }
            ResetPasswordContract.UiEvent.OnPasswordVisibleToggle -> {
                updateState { it.copy(showPassword = !it.showPassword) }
            }
            ResetPasswordContract.UiEvent.OnRePasswordVisibleToggle -> {
                updateState { it.copy(reshowPassword = !it.reshowPassword) }
            }
        }

    }


    private fun updateState(reducer: (ResetPasswordContract.UiState) -> ResetPasswordContract.UiState) {
        _state.value = reducer(_state.value)
    }



}
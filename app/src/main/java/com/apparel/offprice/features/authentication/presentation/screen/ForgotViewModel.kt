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
class ForgotViewModel @Inject constructor(
) : ViewModel(), ForgotContract {
    val _state = MutableStateFlow(ForgotContract.UiState())
    override val state: StateFlow<ForgotContract.UiState> = _state.asStateFlow()

    val _effect = MutableSharedFlow<ForgotContract.UiEffect>()
    override val effect: SharedFlow<ForgotContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: ForgotContract.UiEvent) {
       when(event){
           ForgotContract.UiEvent.OnNavigateBack -> {}
           is ForgotContract.UiEvent.OnValueChangeEmail -> {
               updateState { it.copy(email = event.value) }
           }
       }

    }


    private fun updateState(reducer: (ForgotContract.UiState) -> ForgotContract.UiState) {
        _state.value = reducer(_state.value)
    }



}
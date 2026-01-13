package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.returnFlow.data.ReturnMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseReturnMethodViewModel @Inject constructor() :
    ViewModel(),
    ChooseReturnMethodContract {

    private val _state =
        MutableStateFlow(ChooseReturnMethodContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect =
        MutableSharedFlow<ChooseReturnMethodContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    override fun event(event: ChooseReturnMethodContract.UiEvent) {
        when (event) {

            is ChooseReturnMethodContract.UiEvent.OnMethodSelected -> {
                _state.update {
                    it.copy(selectedMethod = event.method)
                }
            }

            ChooseReturnMethodContract.UiEvent.OnContinueClick -> {
                viewModelScope.launch {
                    when (_state.value.selectedMethod) {
                        ReturnMethod.STORE ->
                            _effect.emit(
                                ChooseReturnMethodContract.UiEffect.NavigateToStoreReturn
                            )

                        ReturnMethod.ADDRESS ->
                            _effect.emit(
                                ChooseReturnMethodContract.UiEffect.NavigateToAddressReturn
                            )

                        null -> Unit // safety
                    }
                }
            }

            ChooseReturnMethodContract.UiEvent.OnCancelClick -> {
                viewModelScope.launch {
                    _effect.emit(
                        ChooseReturnMethodContract.UiEffect.NavigateBack
                    )
                }
            }
        }
    }
}


package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.returnFlow.data.dummyReturns
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestNewReturnViewModel @Inject constructor() : ViewModel(), RequestNewReturnContract {

    private val _state = MutableStateFlow(RequestNewReturnContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RequestNewReturnContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        _state.update {
            it.copy(items = dummyReturns())
        }
    }

    override fun event(event: RequestNewReturnContract.UiEvent) {
        when (event) {

            RequestNewReturnContract.UiEvent.OnBackClick -> {
                emitEffect(RequestNewReturnContract.UiEffect.NavigateBack)
            }

            is RequestNewReturnContract.UiEvent.OnItemSelected -> {
                _state.update { current ->

                    val newSelection =
                        if (current.selectedItemIds.contains(event.itemId)) {
                            current.selectedItemIds - event.itemId // ✅ DESELECT
                        } else {
                            current.selectedItemIds + event.itemId // ✅ SELECT
                        }

                    current.copy(
                        selectedItemIds = newSelection
                    )
                }
            }



            RequestNewReturnContract.UiEvent.OnReasonClick -> {
                _state.update { it.copy(isReasonSheetOpen = true) }
            }

            is RequestNewReturnContract.UiEvent.OnReasonSelected -> {
                _state.update {
                    it.copy(
                        selectedReason = event.reason,
                        isReasonSheetOpen = false
                    )
                }
            }

            RequestNewReturnContract.UiEvent.OnContinueClick -> {
                emitEffect(RequestNewReturnContract.UiEffect.NavigateToNextStep)
            }

            RequestNewReturnContract.UiEvent.OnCloseReasonSheet -> {
                _state.update { it.copy(isReasonSheetOpen = false) }
            }
        }
    }

    private fun emitEffect(effect: RequestNewReturnContract.UiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}

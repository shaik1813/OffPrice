package com.apparel.offprice.features.pdp.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PDPViewModel @Inject constructor(
) : ViewModel(), PDPContract {

    private val _state = MutableStateFlow(PDPContract.UiState())
    override val state: StateFlow<PDPContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<PDPContract.UiEffect>()
    override val effect: SharedFlow<PDPContract.UiEffect> = _effectFlow.asSharedFlow()

    override fun event(event: PDPContract.UiEvent) {
        when (event) {

            PDPContract.UiEvent.onOpenBottomSheetLocation -> {
                viewModelScope.launch {
                    _effectFlow.emit(PDPContract.UiEffect.onOpenBottomSheetLocation)
                }
            }

            PDPContract.UiEvent.onCloseBottomSheetLocation -> {
                viewModelScope.launch {
                    _effectFlow.emit(PDPContract.UiEffect.onCloseBottomSheetLocation)
                }
            }

            PDPContract.UiEvent.onOpenAddToBagSheet -> {
                viewModelScope.launch {
                    updateState{it.copy(
                        isSizeSelectSheet = false)
                    }

                    delay(350)

                    updateState { it.copy( isAddBasketSheet = true ) }
                }
            }

            PDPContract.UiEvent.onCloseAddToBagSheet -> {
                viewModelScope.launch {
                    updateState{it.copy( isAddBasketSheet = false)}
                }
            }

            PDPContract.UiEvent.onOpenSizeSelectSheet -> {
                viewModelScope.launch {
                    updateState{it.copy( isSizeSelectSheet = true)}
                }
            }

            PDPContract.UiEvent.onCloseSizeSelectSheet -> {
                viewModelScope.launch {
                    updateState{it.copy( isSizeSelectSheet = false)}
                }
            }

            PDPContract.UiEvent.onOpenShareProductSheet -> {
                viewModelScope.launch {
                    updateState{it.copy( isShareProductSheet = true)}
                }
            }

            PDPContract.UiEvent.onCloseShareProductSheet -> {
                viewModelScope.launch {
                    updateState{it.copy( isShareProductSheet = false)}
                }
            }

            PDPContract.UiEvent.onOpenSizeGuideSheet -> {
                viewModelScope.launch {
                    updateState { it.copy(isSizeGuideSheet = true) }
                }
            }

            PDPContract.UiEvent.onCloseSizeGuideSheet -> {
                viewModelScope.launch {
                    updateState { it.copy(isSizeGuideSheet = false) }
                }
            }


        }
    }

    private fun updateState(reducer: (PDPContract.UiState) -> PDPContract.UiState) {
        _state.value = reducer(_state.value)
    }
}
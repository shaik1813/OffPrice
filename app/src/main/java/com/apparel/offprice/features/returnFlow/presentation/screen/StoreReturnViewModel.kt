package com.apparel.offprice.features.returnFlow.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.checkout.data.samplePickupStores
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreReturnViewModel @Inject constructor() : ViewModel(), StoreReturnContract {

    private val _state =
        MutableStateFlow(StoreReturnContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect =
        MutableSharedFlow<StoreReturnContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    init {
        loadStores()
    }

    private fun loadStores() {
        _state.update {
            it.copy(
                stores = samplePickupStores // replace with API later
            )
        }
    }

    override fun event(event: StoreReturnContract.UiEvent) {
        when (event) {

            //Store selection
            is StoreReturnContract.UiEvent.OnStoreSelected -> {
                _state.update {
                    it.copy(selectedStoreId = event.storeId)
                }
            }

            //  Continue → Refund Sheet
            StoreReturnContract.UiEvent.OnContinueClick -> {
                Log.e("StoreReturn", "Continue clicked")
                _state.update { it.copy(isRefundSheetOpen = true) }
            }

            //  Refund Flow
            is StoreReturnContract.UiEvent.OnRefundMethodSelected -> {
                _state.update {
                    it.copy(selectedRefundMethod = event.method)
                }
            }

            StoreReturnContract.UiEvent.OnReturnItemClick -> {
                _state.update {
                    it.copy(
                        isRefundSheetOpen = false,
                        showConfirmDialog = true
                    )
                }
            }

            StoreReturnContract.UiEvent.OnConfirmReturn -> {
                _state.update {
                    it.copy(
                        showConfirmDialog = false,
                        showSuccessDialog = true
                    )
                }
            }

            StoreReturnContract.UiEvent.OnCancelReturn -> {
                _state.update {
                    it.copy(showConfirmDialog = false)
                }
            }

            StoreReturnContract.UiEvent.OnSuccessOkayClick -> {
                emitEffect(StoreReturnContract.UiEffect.FinishFlow)
            }

            StoreReturnContract.UiEvent.OnRefundSheetDismiss -> {
                _state.update { it.copy(isRefundSheetOpen = false) }
            }

            //  Back
            StoreReturnContract.UiEvent.OnCancelClick -> {
                emitEffect(StoreReturnContract.UiEffect.NavigateBack)
            }
        }
    }

    private fun emitEffect(effect: StoreReturnContract.UiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}

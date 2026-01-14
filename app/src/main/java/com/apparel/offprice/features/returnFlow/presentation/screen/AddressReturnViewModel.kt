package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.returnFlow.data.sampleAddresses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressReturnViewModel @Inject constructor() : ViewModel(), AddressReturnContract {

    private val _state =
        MutableStateFlow(AddressReturnContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect =
        MutableSharedFlow<AddressReturnContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    init {
        loadAddresses()
    }

    private fun loadAddresses() {
        _state.update {
            it.copy(
                addresses = sampleAddresses // replace with API
            )
        }
    }

    override fun event(event: AddressReturnContract.UiEvent) {
        when (event) {

            // Address selection
            is AddressReturnContract.UiEvent.OnAddressSelected -> {
                _state.update {
                    it.copy(selectedAddressId = event.addressId)
                }
            }

            AddressReturnContract.UiEvent.OnAddNewAddressClick -> {
                emitEffect(AddressReturnContract.UiEffect.NavigateToAddAddress)
            }

            is AddressReturnContract.UiEvent.OnEditAddress -> {
                // keep for later
            }

            is AddressReturnContract.UiEvent.OnDeleteAddress -> {
                // keep for later
            }

            // Continue → Refund Sheet
            AddressReturnContract.UiEvent.OnContinueClick -> {
                _state.update {
                    it.copy(isRefundSheetOpen = true)
                }
            }

            //  Refund flow
            is AddressReturnContract.UiEvent.OnRefundMethodSelected -> {
                _state.update {
                    it.copy(selectedRefundMethod = event.method)
                }
            }

            AddressReturnContract.UiEvent.OnReturnItemClick -> {
                _state.update {
                    it.copy(
                        isRefundSheetOpen = false,
                        showConfirmDialog = true
                    )
                }
            }

            AddressReturnContract.UiEvent.OnConfirmReturn -> {
                _state.update {
                    it.copy(
                        showConfirmDialog = false,
                        showSuccessDialog = true
                    )
                }
            }

            AddressReturnContract.UiEvent.OnCancelReturn -> {
                _state.update {
                    it.copy(showConfirmDialog = false)
                }
            }

            AddressReturnContract.UiEvent.OnSuccessOkayClick -> {
                emitEffect(AddressReturnContract.UiEffect.FinishFlow)
            }

            //  Back
            AddressReturnContract.UiEvent.OnCancelClick -> {
                emitEffect(AddressReturnContract.UiEffect.NavigateBack)
            }

            is AddressReturnContract.UiEvent.OnRefundSheetDismiss -> {
                _state.update { it.copy(isRefundSheetOpen = false) }
            }
        }
    }

    private fun emitEffect(effect: AddressReturnContract.UiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}

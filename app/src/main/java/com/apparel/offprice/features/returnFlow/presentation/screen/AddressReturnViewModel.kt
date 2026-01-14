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

            is AddressReturnContract.UiEvent.OnAddressSelected -> {
                _state.update {
                    it.copy(selectedAddressId = event.addressId)
                }
            }

            AddressReturnContract.UiEvent.OnAddNewAddressClick -> {
                emitEffect(AddressReturnContract.UiEffect.NavigateToAddAddress)
            }

            is AddressReturnContract.UiEvent.OnEditAddress -> {
                // optional: route to edit screen
            }

            is AddressReturnContract.UiEvent.OnDeleteAddress -> {
                // optional: delete logic
            }

            AddressReturnContract.UiEvent.OnContinueClick -> {
                emitEffect(AddressReturnContract.UiEffect.NavigateNext)
            }

            AddressReturnContract.UiEvent.OnCancelClick -> {
                emitEffect(AddressReturnContract.UiEffect.NavigateBack)
            }
        }
    }

    private fun emitEffect(effect: AddressReturnContract.UiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}

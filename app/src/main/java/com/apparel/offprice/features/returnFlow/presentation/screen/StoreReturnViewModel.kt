package com.apparel.offprice.features.returnFlow.presentation.screen

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

            is StoreReturnContract.UiEvent.OnStoreSelected -> {
                _state.update {
                    it.copy(selectedStoreId = event.storeId)
                }
            }

            StoreReturnContract.UiEvent.OnContinueClick -> {
                viewModelScope.launch {
                    _effect.emit(StoreReturnContract.UiEffect.NavigateNext)
                }
            }

            StoreReturnContract.UiEvent.OnCancelClick -> {
                viewModelScope.launch {
                    _effect.emit(StoreReturnContract.UiEffect.NavigateBack)
                }
            }
        }
    }
}

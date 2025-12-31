package com.apparel.offprice.features.checkout.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.storeCredit.presentation.screen.StoreCreditContract
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
class CheckOutViewModel @Inject constructor() : ViewModel(), CheckOutContract {

    private val _state = MutableStateFlow(CheckOutContract.UiState())
    override val state: StateFlow<CheckOutContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CheckOutContract.UiEffect>()
    override val effect: SharedFlow<CheckOutContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: CheckOutContract.UiEvent) {
        when (event) {
            CheckOutContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(CheckOutContract.UiEffect.OnNavigateToBack)
                }
            }

            CheckOutContract.UiEvent.OnCleared -> TODO()
            is CheckOutContract.UiEvent.OnFilterSelected -> {
                _state.update { current ->
                    current.copy(selectedFilter = event.filter)
                }
            }
        }
    }
}
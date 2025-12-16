package com.apparel.offprice.features.cart.presentation.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
) : ViewModel(), CartContract {

    private val _state = MutableStateFlow(CartContract.UiState())
    override val state: StateFlow<CartContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<CartContract.UiEffect>()
    override val effect: SharedFlow<CartContract.UiEffect> = _effectFlow.asSharedFlow()

    override fun event(event: CartContract.UiEvent) {
        when (event) {

            CartContract.UiEvent.onOpenBottomSheetOffer -> {
                viewModelScope.launch {
                    updateState { it.copy(isCouponOfferSheet = true) }
                }
            }

            CartContract.UiEvent.onCloseBottomSheetOffer -> {
                viewModelScope.launch {
                    updateState { it.copy(isCouponOfferSheet = false) }
                }
            }

            CartContract.UiEvent.onOpenDeleteCartConfirm -> {
                viewModelScope.launch {
                    updateState { it.copy(isDeleteCartDialog = true) }
                }
            }

            CartContract.UiEvent.onCloseDeleteCartConfirm -> {
                viewModelScope.launch {
                    updateState { it.copy(isDeleteCartDialog = false) }
                }
            }

        }
    }

    private fun updateState(reducer: (CartContract.UiState) -> CartContract.UiState) {
        _state.value = reducer(_state.value)
    }
}
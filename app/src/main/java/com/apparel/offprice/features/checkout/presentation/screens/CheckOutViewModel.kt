package com.apparel.offprice.features.checkout.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.checkout.presentation.components.CheckoutStep
import com.apparel.offprice.features.checkout.presentation.components.ShippingAddressFilter
import com.apparel.offprice.features.checkout.presentation.components.sampleAddresses
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

            CheckOutContract.UiEvent.OnShipFeeClick -> {
                viewModelScope.launch {
                    updateState { it.copy(isOpenShipFee = !it.isOpenShipFee) }
                }
            }

            CheckOutContract.UiEvent.OnCleared -> TODO()
            is CheckOutContract.UiEvent.OnFilterSelected -> {
                _state.update { current ->

                    if (event.filter == ShippingAddressFilter.DELIVERY) {
                        // ⬅ Coming BACK to delivery
                        current.copy(
                            selectedFilter = event.filter,
                            checkoutStep =
                                if (current.isAddressConfirmed)
                                    CheckoutStep.SUMMARY
                                else
                                    CheckoutStep.ADDRESS
                        )
                    } else {
                        // ⬅ Pickup — no checkout step logic
                        current.copy(
                            selectedFilter = event.filter
                        )
                    }
                }
            }

            //SAVE ADDRESS
            CheckOutContract.UiEvent.OnSaveAddressClicked -> {
                _state.update {
                    it.copy(
                        checkoutStep = CheckoutStep.SUMMARY,
                        isAddressConfirmed = true,
                        isAddressSaved = true,
                        // if user didn’t explicitly pick, take first address
                        selectedAddress = it.selectedAddress ?: sampleAddresses.first()
                    )
                }
            }

            //PROCEED TO PAYMENT
            CheckOutContract.UiEvent.OnProceedToPaymentClicked -> {
                viewModelScope.launch {
                    _effect.emit(CheckOutContract.UiEffect.NavigateToPayment)
                }
            }

            CheckOutContract.UiEvent.OnOpenAddressSheet -> {
                _state.update { it.copy(isAddressSheetOpen = true) }
            }

            CheckOutContract.UiEvent.OnCloseAddressSheet -> {
                _state.update { it.copy(isAddressSheetOpen = false) }
            }

            is CheckOutContract.UiEvent.OnAddressSelected -> {
                _state.update {
                    it.copy(
                        selectedAddress = event.address,
                        isAddressSheetOpen = false,
                        isAddressSaved = true
                    )
                }
            }
            CheckOutContract.UiEvent.OnOpenAddAddress -> {
                //close address list first
                _state.update {
                    it.copy(
                        isAddressSheetOpen = false,
                        isAddAddressSheetOpen = true
                    )
                }
            }

            CheckOutContract.UiEvent.OnCloseAddAddress -> {
                _state.update {
                    it.copy(isAddAddressSheetOpen = false)
                }
            }
        }
    }

    private fun updateState(reducer: (CheckOutContract.UiState) -> CheckOutContract.UiState) {
        _state.value = reducer(_state.value)
    }
}


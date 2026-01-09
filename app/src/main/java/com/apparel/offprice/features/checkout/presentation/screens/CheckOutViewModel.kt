package com.apparel.offprice.features.checkout.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.checkout.data.AddAddressFilter
import com.apparel.offprice.features.checkout.data.AddressSheetMode
import com.apparel.offprice.features.checkout.data.CheckoutStep
import com.apparel.offprice.features.checkout.data.PaymentMethod
import com.apparel.offprice.features.checkout.data.PaymentResult
import com.apparel.offprice.features.checkout.data.ShippingAddressFilter
import com.apparel.offprice.features.checkout.data.sampleAddresses
import com.apparel.offprice.features.checkout.data.samplePickupStores
import com.apparel.offprice.features.checkout.data.savedCards
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

            /* is CheckOutContract.UiEvent.OnFilterSelected -> {
                 _state.update { current ->

                     if (current.checkoutStep == CheckoutStep.PAYMENT) {
                         //Toggle disabled once payment starts
                         current
                     } else if (event.filter == ShippingAddressFilter.DELIVERY) {
                         current.copy(
                             selectedFilter = event.filter,
                             checkoutStep =
                                 if (current.isAddressConfirmed)
                                     CheckoutStep.SUMMARY
                                 else
                                     CheckoutStep.ADDRESS
                         )
                     } else {
                         current.copy(selectedFilter = event.filter)
                     }
                 }
             }*/

            is CheckOutContract.UiEvent.OnFilterSelected -> {
                _state.update { current ->

                    // 🚫 Toggle disabled once payment starts
                    if (current.checkoutStep == CheckoutStep.PAYMENT) {
                        current
                    }

                    // 🚚 DELIVERY FLOW (unchanged)
                    else if (event.filter == ShippingAddressFilter.DELIVERY) {
                        current.copy(
                            selectedFilter = event.filter,
                            checkoutStep =
                                if (current.isAddressConfirmed)
                                    CheckoutStep.SUMMARY
                                else
                                    CheckoutStep.ADDRESS
                        )
                    }

                    // 🏬 PICKUP FLOW (NEW + SAFE)
                    else {
                        current.copy(
                            selectedFilter = ShippingAddressFilter.PICKUPATSTORE,
                            pickupStores =
                                if (current.pickupStores.isEmpty()) {
                                    samplePickupStores
                                } else {
                                    current.pickupStores
                                },
                            selectedPickupStore = null,
                            checkoutStep = CheckoutStep.ADDRESS // stays at Address step
                        )
                    }
                }
            }

            is CheckOutContract.UiEvent.OnPickupStoreSelected -> {
                _state.update {
                    it.copy(
                        selectedPickupStore = event.store,
                        isPickupStoreSelected = true
                    )
                }
            }

            CheckOutContract.UiEvent.OnChangePickupStore -> {
                _state.update {
                    it.copy(
                        selectedPickupStore = null,
                        isPickupStoreSelected = false
                    )
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
                _state.update {
                    it.copy(
                        checkoutStep = CheckoutStep.PAYMENT,
                        currentStep = 2
                    )
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
                _state.update {
                    it.copy(
                        isAddAddressSheetOpen = true,
                        addressSheetMode = AddressSheetMode.ADD,
                        editingAddress = null
                    )
                }
            }

            is CheckOutContract.UiEvent.OnEditAddress -> {
                _state.update {
                    it.copy(
                        isAddAddressSheetOpen = true,
                        addressSheetMode = AddressSheetMode.EDIT,
                        editingAddress = event.address,
                        selectedAddType = AddAddressFilter.valueOf(event.address.label.uppercase()),
                        isDefaultAddress = false // or map from model
                    )
                }
            }

            CheckOutContract.UiEvent.OnCloseAddAddress -> {
                _state.update {
                    it.copy(isAddAddressSheetOpen = false)
                }
            }

            is CheckOutContract.UiEvent.OnAddAddressTypeSelected -> {
                _state.update {
                    it.copy(addAddressType = event.type)
                }
            }

            is CheckOutContract.UiEvent.OnDefaultAddressChecked -> {
                _state.update {
                    it.copy(markAsDefault = event.checked)
                }
            }

            CheckOutContract.UiEvent.OnSaveNewAddress -> {
                // Simulate saving address
                _state.update {
                    it.copy(
                        isAddAddressSheetOpen = false,
                        isAddressSaved = true,
                        selectedAddress = sampleAddresses.first() // replace with real one later
                    )
                }
            }

            //Payment
            is CheckOutContract.UiEvent.OnSavedCardSelected -> {
                _state.update {
                    it.copy(selectedSavedCardId = event.id)
                }
            }

            is CheckOutContract.UiEvent.OnPaymentMethodClicked -> {
                _state.update {
                    it.copy(
                        selectedPayment = event.method,
                        expandedPayment =
                            if (it.expandedPayment == event.method) null else event.method,
                        selectedSavedCardId =
                            if (event.method == PaymentMethod.SAVED && it.selectedSavedCardId == null)
                                savedCards.first().id
                            else it.selectedSavedCardId
                    )
                }
            }


            CheckOutContract.UiEvent.OnPayClicked -> {
                viewModelScope.launch {

                    //Simulate payment
                    delay(1500)

                    val success = true // replace with real API result

                    _state.update {
                        it.copy(
                            paymentResult =
                                if (success)
                                    PaymentResult.Success(orderId = "296724720911582")
                                else
                                    PaymentResult.Failure(orderId = "296724720911582")
                        )
                    }
                }
            }

            //Payment Result
            is CheckOutContract.UiEvent.OnRetryPayment -> {
                _state.update {
                    it.copy(
                        paymentResult = null,
                        checkoutStep = CheckoutStep.PAYMENT
                    )
                }
            }

            CheckOutContract.UiEvent.OnContinueShopping -> {
                // Navigate to Home / Clear checkout stack
            }

            CheckOutContract.UiEvent.OnMyOrders -> {
                // Navigate to Orders screen
            }


            //PICKUPSTORE
            is CheckOutContract.UiEvent.OnPickupStoreSelected -> {
                _state.update {
                    it.copy(selectedPickupStore = event.store)
                }
            }

            is CheckOutContract.UiEvent.OnPickupNameChanged -> {
                _state.update {
                    it.copy(pickupName = event.value)
                }
            }

            is CheckOutContract.UiEvent.OnPickupPhoneChanged -> {
                _state.update {
                    it.copy(pickupPhone = event.value)
                }
            }


        }
    }

    private fun updateState(reducer: (CheckOutContract.UiState) -> CheckOutContract.UiState) {
        _state.value = reducer(_state.value)
    }
}


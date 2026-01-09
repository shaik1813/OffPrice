package com.apparel.offprice.features.checkout.presentation.screens

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.checkout.data.AddAddressFilter
import com.apparel.offprice.features.checkout.data.AddressSheetMode
import com.apparel.offprice.features.checkout.data.AddressUiModel
import com.apparel.offprice.features.checkout.data.CheckoutStep
import com.apparel.offprice.features.checkout.data.PaymentMethod
import com.apparel.offprice.features.checkout.data.PaymentResult
import com.apparel.offprice.features.checkout.data.PickupStore
import com.apparel.offprice.features.checkout.data.ProductItem
import com.apparel.offprice.features.checkout.data.ShippingAddressFilter

interface CheckOutContract : UnidirectionalViewModel<
        CheckOutContract.UiState, CheckOutContract.UiEvent, CheckOutContract.UiEffect> {

    data class UiState(
        val totalBalance: Double = 0.0,
        val totalReceived: Double = 0.0,
        val totalUsed: Double = 0.0,
        val selectedFilter: ShippingAddressFilter = ShippingAddressFilter.DELIVERY,
        val checkoutStep: CheckoutStep = CheckoutStep.ADDRESS,
        val isAddressConfirmed: Boolean = false,
        val isAddressSaved: Boolean = false,
        val transactions: List<ProductItem> = emptyList(),
        val isLoading: Boolean = false,
        val isOpenShipFee: Boolean = false,
        val isCartEmpty: Boolean = false,
        val isAddressSheetOpen: Boolean = false,
        val isAddAddressSheetOpen: Boolean = false,
        val selectedAddress: AddressUiModel? = null,
        val addAddressType: AddAddressFilter = AddAddressFilter.HOME,
        val markAsDefault: Boolean = false,
        val addressSheetMode: AddressSheetMode = AddressSheetMode.ADD,
        val editingAddress: AddressUiModel? = null,
        val selectedAddType: AddAddressFilter = AddAddressFilter.HOME,
        val isDefaultAddress: Boolean = false,
        val currentStep: Int = 1,

        // PAYMENT
        val selectedSavedCardId: String? = null,
        val selectedPayment: PaymentMethod = PaymentMethod.SAVED,
        val expandedPayment: PaymentMethod? = PaymentMethod.SAVED,

        // Result
        val paymentResult: PaymentResult? = null,

        // PICKUPSTORE
        val pickupStores: List<PickupStore> = emptyList(),
        val selectedPickupStore: PickupStore? = null,
        val isPickupStoreSelected: Boolean = false,

        val pickupName: String = "",
        val pickupPhone: String = ""
    )

    sealed interface UiEvent {
        data class OnFilterSelected(val filter: ShippingAddressFilter) : UiEvent
        data object OnBackPressed : UiEvent
        data object OnCleared : UiEvent
        data object OnShipFeeClick : UiEvent
        data object OnSaveAddressClicked : UiEvent
        data object OnProceedToPaymentClicked : UiEvent

        data object OnOpenAddressSheet : UiEvent
        data object OnCloseAddressSheet : UiEvent
        data class OnAddressSelected(val address: AddressUiModel) : UiEvent

        // Add address
        object OnOpenAddAddress : UiEvent
        object OnCloseAddAddress : UiEvent
        data class OnEditAddress(val address: AddressUiModel) : UiEvent
        data class OnAddAddressTypeSelected(val type: AddAddressFilter) : UiEvent
        data class OnDefaultAddressChecked(val checked: Boolean) : UiEvent
        data object OnSaveNewAddress : UiEvent

        // PAYMENT
        data class OnSavedCardSelected(val id: String) : UiEvent
        data class OnPaymentMethodClicked(val method: PaymentMethod) : UiEvent

        // Payment action
        object OnPayClicked : UiEvent


        // Result actions
        object OnContinueShopping : UiEvent
        object OnMyOrders : UiEvent
        object OnRetryPayment : UiEvent


        // PICKUP
        data class OnPickupStoreSelected(val store: PickupStore) : UiEvent
        data object OnChangePickupStore : UiEvent
        data class OnPickupNameChanged(val value: String) : UiEvent
        data class OnPickupPhoneChanged(val value: String) : UiEvent
    }

    sealed interface UiEffect {
        data object OnNavigateToBack : UiEffect
        data object NavigateToPayment : UiEffect
    }
}
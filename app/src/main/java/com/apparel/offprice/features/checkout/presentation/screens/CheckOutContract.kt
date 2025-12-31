package com.apparel.offprice.features.checkout.presentation.screens

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.checkout.presentation.components.AddressUiModel
import com.apparel.offprice.features.checkout.presentation.components.CheckoutStep
import com.apparel.offprice.features.checkout.presentation.components.ProductItem
import com.apparel.offprice.features.checkout.presentation.components.ShippingAddressFilter

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
    )

    sealed interface UiEvent {
        data class OnFilterSelected(val filter: ShippingAddressFilter) : UiEvent
        data object OnBackPressed : UiEvent
        data object OnCleared : UiEvent
        object OnShipFeeClick : UiEvent
        data object OnSaveAddressClicked : UiEvent
        data object OnProceedToPaymentClicked : UiEvent

        data object OnOpenAddressSheet : UiEvent
        data object OnCloseAddressSheet : UiEvent
        data class OnAddressSelected(val address: AddressUiModel) : UiEvent
        // Add address
        object OnOpenAddAddress : UiEvent
        object OnCloseAddAddress : UiEvent

    }

    sealed interface UiEffect {
        data object OnNavigateToBack : UiEffect
        data object NavigateToPayment : UiEffect
    }
}
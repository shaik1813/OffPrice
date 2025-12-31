package com.apparel.offprice.features.checkout.presentation.screens

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.checkout.presentation.components.ShippingAddressFilter
import com.apparel.offprice.features.storeCredit.data.StoreCreditTransaction

interface CheckOutContract : UnidirectionalViewModel<
        CheckOutContract.UiState, CheckOutContract.UiEvent, CheckOutContract.UiEffect> {

    data class UiState(
        val totalBalance: Double = 0.0,
        val totalReceived: Double = 0.0,
        val totalUsed: Double = 0.0,
        val selectedFilter: ShippingAddressFilter = ShippingAddressFilter.DELIVERY,
        val transactions: List<StoreCreditTransaction> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data class OnFilterSelected(val filter: ShippingAddressFilter) : UiEvent
        data object OnBackPressed : UiEvent
        data object OnCleared : UiEvent
    }

    sealed interface UiEffect {
        data object OnNavigateToBack : UiEffect
    }
}
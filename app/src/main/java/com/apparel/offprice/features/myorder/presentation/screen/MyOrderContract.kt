package com.apparel.offprice.features.myorder.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.myorder.data.OrderFilter
import com.apparel.offprice.features.myorder.data.OrderItem

interface MyOrderContract : UnidirectionalViewModel
<MyOrderContract.UiState, MyOrderContract.UiEvent, MyOrderContract.UiEffect> {

    data class UiState(
        val isGuestUser: Boolean = false,
        val orderData: List<OrderItem> = emptyList(),
        val isFilterBottomSheetOpen: Boolean = false,
        val selectedFilter: OrderFilter = OrderFilter.ALL
    )


    sealed interface UiEvent {
        data class OnCountrySelected(val country: Country) : MyOrderContract.UiEvent
        data object OnBackPressed : UiEvent
        data object OnAllOrdersClicked : UiEvent
        data class OnFilterSelected(val filter: OrderFilter) : UiEvent
        data object OnDismissBottomSheet : UiEvent

    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect

    }

}
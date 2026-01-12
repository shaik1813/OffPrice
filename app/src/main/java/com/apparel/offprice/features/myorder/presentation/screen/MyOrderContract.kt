package com.apparel.offprice.features.myorder.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.myorder.data.OrderItem
import com.apparel.offprice.features.profile.presentation.screen.userprofile.UserProfileContract

interface MyOrderContract : UnidirectionalViewModel
<MyOrderContract.UiState, MyOrderContract.UiEvent, MyOrderContract.UiEffect> {

    data class UiState(
        val isGuestUser: Boolean = false,
        val orderData: List<OrderItem> = emptyList()
    )


    sealed interface UiEvent {
        data class OnCountrySelected(val country: Country) : MyOrderContract.UiEvent
        data object OnBackPressed : UiEvent

    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect

    }

}
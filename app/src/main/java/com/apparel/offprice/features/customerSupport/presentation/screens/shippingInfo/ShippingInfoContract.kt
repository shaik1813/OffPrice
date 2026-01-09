package com.apparel.offprice.features.customerSupport.presentation.screens.shippingInfo

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface ShippingInfoContract: UnidirectionalViewModel<
        ShippingInfoContract.UiState,
        ShippingInfoContract.UiEvent,
        ShippingInfoContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
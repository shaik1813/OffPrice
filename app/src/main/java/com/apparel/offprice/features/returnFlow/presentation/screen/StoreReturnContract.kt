package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.checkout.data.PickupStore

interface StoreReturnContract : UnidirectionalViewModel<
            StoreReturnContract.UiState, StoreReturnContract.UiEvent, StoreReturnContract.UiEffect> {

    data class UiState(
        val stores: List<PickupStore> = emptyList(),
        val selectedStoreId: String? = null
    )

    sealed interface UiEvent {
        data class OnStoreSelected(val storeId: String) : UiEvent
        data object OnContinueClick : UiEvent
        data object OnCancelClick : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateNext : UiEffect
    }
}

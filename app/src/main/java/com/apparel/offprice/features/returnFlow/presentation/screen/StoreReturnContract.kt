package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.checkout.data.PickupStore
import com.apparel.offprice.features.returnFlow.data.RefundMethod

interface StoreReturnContract : UnidirectionalViewModel<
            StoreReturnContract.UiState, StoreReturnContract.UiEvent, StoreReturnContract.UiEffect> {

    data class UiState(
        val stores: List<PickupStore> = emptyList(),
        val selectedStoreId: String? = null,

        // 🔹 SAME AS ADDRESS RETURN
        val isRefundSheetOpen: Boolean = false,
        val selectedRefundMethod: RefundMethod? = null,
        val showConfirmDialog: Boolean = false,
        val showSuccessDialog: Boolean = false
    )

    sealed interface UiEvent {
        data class OnStoreSelected(val storeId: String) : UiEvent
        data object OnContinueClick : UiEvent
        data object OnCancelClick : UiEvent

        // 🔹 Refund flow
        data class OnRefundMethodSelected(val method: RefundMethod) : UiEvent
        data object OnReturnItemClick : UiEvent
        data object OnConfirmReturn : UiEvent
        data object OnCancelReturn : UiEvent
        data object OnSuccessOkayClick : UiEvent
        data object OnRefundSheetDismiss : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateNext : UiEffect
        data object FinishFlow : UiEffect
    }
}

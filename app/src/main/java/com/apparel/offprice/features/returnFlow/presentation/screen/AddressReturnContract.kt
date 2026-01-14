package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.returnFlow.data.DeliveryAddressModel2
import com.apparel.offprice.features.returnFlow.data.RefundMethod

interface AddressReturnContract : UnidirectionalViewModel<
            AddressReturnContract.UiState, AddressReturnContract.UiEvent, AddressReturnContract.UiEffect> {

    data class UiState(
        val addresses: List<DeliveryAddressModel2> = emptyList(),
        val selectedAddressId: String? = null,

        // NEW
        val isRefundSheetOpen: Boolean = false,
        val selectedRefundMethod: RefundMethod? = null,
        val showConfirmDialog: Boolean = false,
        val showSuccessDialog: Boolean = false
    )

    sealed interface UiEvent {
        data class OnAddressSelected(val addressId: String) : UiEvent
        data object OnAddNewAddressClick : UiEvent
        data class OnEditAddress(val address: DeliveryAddressModel2) : UiEvent
        data class OnDeleteAddress(val address: DeliveryAddressModel2) : UiEvent
        data object OnContinueClick : UiEvent
        data object OnCancelClick : UiEvent

        // Refund flow
        data class OnRefundMethodSelected(val method: RefundMethod) : UiEvent
        data object OnReturnItemClick : UiEvent
        data object OnConfirmReturn : UiEvent
        data object OnCancelReturn : UiEvent
        data object OnSuccessOkayClick : UiEvent
        object OnRefundSheetDismiss : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateNext : UiEffect
        data object NavigateToAddAddress : UiEffect

        data object FinishFlow : UiEffect
    }
}

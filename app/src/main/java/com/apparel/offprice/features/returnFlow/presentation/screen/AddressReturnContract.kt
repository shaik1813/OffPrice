package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.returnFlow.data.DeliveryAddressModel2

interface AddressReturnContract : UnidirectionalViewModel<
            AddressReturnContract.UiState, AddressReturnContract.UiEvent, AddressReturnContract.UiEffect> {

    data class UiState(
        val addresses: List<DeliveryAddressModel2> = emptyList(),
        val selectedAddressId: String? = null
    )

    sealed interface UiEvent {
        data class OnAddressSelected(val addressId: String) : UiEvent
        data object OnAddNewAddressClick : UiEvent
        data class OnEditAddress(val address: DeliveryAddressModel2) : UiEvent
        data class OnDeleteAddress(val address: DeliveryAddressModel2) : UiEvent
        data object OnContinueClick : UiEvent
        data object OnCancelClick : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateNext : UiEffect
        data object NavigateToAddAddress : UiEffect
    }
}

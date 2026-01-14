package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.returnFlow.data.ReturnMethod

interface ChooseReturnMethodContract : UnidirectionalViewModel<
            ChooseReturnMethodContract.UiState, ChooseReturnMethodContract.UiEvent, ChooseReturnMethodContract.UiEffect> {

    data class UiState(
        val selectedMethod: ReturnMethod? = null
    )

    sealed interface UiEvent {
        data class OnMethodSelected(val method: ReturnMethod) : UiEvent
        data object OnContinueClick : UiEvent
        data object OnCancelClick : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateToStoreReturn : UiEffect
        data object NavigateToAddressReturn : UiEffect
    }

}

package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.returnFlow.data.ReturnItem
import com.apparel.offprice.features.returnFlow.data.ReturnReason

interface RequestNewReturnContract : UnidirectionalViewModel<
            RequestNewReturnContract.UiState, RequestNewReturnContract.UiEvent, RequestNewReturnContract.UiEffect> {

    data class UiState(
        val items: List<ReturnItem> = emptyList(),
        val selectedItemIds: Set<String> = emptySet(),
        val selectedReason: ReturnReason? = null,
        val isReasonSheetOpen: Boolean = false
    )

    sealed interface UiEvent {

        data object OnBackClick : UiEvent

        data class OnItemSelected(val itemId: String) : UiEvent

        data object OnReasonClick : UiEvent

        data class OnReasonSelected(val reason: ReturnReason) : UiEvent

        data object OnContinueClick : UiEvent

        data object OnCloseReasonSheet : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateToNextStep : UiEffect
        data class ShowError(val message: String) : UiEffect
    }
}

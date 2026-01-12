package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.returnFlow.data.ReturnItem

interface ReturnDetailsContract : UnidirectionalViewModel<
            ReturnDetailsContract.UiState, ReturnDetailsContract.UiEvent, ReturnDetailsContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = true,
        val returnItem: ReturnItem? = null
    )

    sealed interface UiEvent {
        data object OnBackClick : UiEvent
        data object OnCleared : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data class ShowError(val message: String) : UiEffect
    }
}

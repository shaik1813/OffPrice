package com.apparel.offprice.features.returnFlow.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.returnFlow.data.ReturnFilter
import com.apparel.offprice.features.returnFlow.data.ReturnItem

interface ReturnsContract : UnidirectionalViewModel<
        ReturnsContract.UiState, ReturnsContract.UiEvent, ReturnsContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = false,
        val returnsList: List<ReturnItem> = emptyList(),
        val selectedFilter: ReturnFilter = ReturnFilter.LAST_3_MONTHS,
        val isFilterBottomSheetOpen: Boolean = false
    )

    sealed interface UiEvent {

        data object OnBackClick : UiEvent

        data object OnRequestNewReturnClick : UiEvent

        data class OnReturnItemClick(val returnId: String) : UiEvent

        data object OnFilterClick : UiEvent

        data class OnFilterSelected(val filter: ReturnFilter) : UiEvent

        data object OnFilterSubmit : UiEvent

        data object OnCleared : UiEvent
    }

    sealed interface UiEffect {

        data object NavigateBack : UiEffect

        data object NavigateToRequestReturn : UiEffect

        data class NavigateToReturnDetails(val returnId: String) : UiEffect

        data class ShowError(val message: String) : UiEffect
    }
}

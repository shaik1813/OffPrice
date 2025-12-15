package com.apparel.offprice.features.storeCredit.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.storeCredit.data.StoreCreditFilter
import com.apparel.offprice.features.storeCredit.data.StoreCreditTransaction

interface StoreCreditContract : UnidirectionalViewModel<
        StoreCreditContract.UiState, StoreCreditContract.UiEvent, StoreCreditContract.UiEffect> {

    data class UiState(
        val totalBalance: Double = 0.0,
        val totalReceived: Double = 0.0,
        val totalUsed: Double = 0.0,
        val selectedFilter: StoreCreditFilter = StoreCreditFilter.ALL,
        val transactions: List<StoreCreditTransaction> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data class OnFilterSelected(val filter: StoreCreditFilter) : UiEvent
        data object OnBackPressed : UiEvent
        data object OnCleared : UiEvent
    }

    sealed interface UiEffect {
        data object OnNavigateToBack : UiEffect
    }
}


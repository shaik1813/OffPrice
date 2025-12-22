package com.apparel.offprice.features.home.presentation.screens.home

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.LOneCategoryItem

interface HomeContract :
    UnidirectionalViewModel<HomeContract.UiState, HomeContract.UiEvent, HomeContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = false,
        val lOneCategoryItems: List<LOneCategoryItem> = emptyList(),

        )

    sealed interface UiEvent {
        data object OnSearch: UiEvent
        data object OnWishlist: UiEvent
        data class OnLOneCategoryItemClick(val item: LOneCategoryItem): UiEvent
    }

    sealed interface UiEffect {
        data object ShowMessage: UiEffect
    }
}
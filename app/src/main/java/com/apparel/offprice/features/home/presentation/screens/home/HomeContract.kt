package com.apparel.offprice.features.home.presentation.screens.home

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.LOneCategoryItem

interface HomeContract :
    UnidirectionalViewModel<HomeContract.UiState, HomeContract.UiEvent, HomeContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = false,
        val lOneCategoryList: List<LOneCategoryItem> = emptyList(),
        val selectedIndex: Int = -1

    )

    sealed interface UiEvent {
        data object OnSearch: UiEvent
        data object OnWishlist: UiEvent
        data class OnLOneCategoryItemClick(val index: Int, val item: LOneCategoryItem): UiEvent
    }

    sealed interface UiEffect {
        data object ShowMessage: UiEffect
        data object OnSearchClicked: UiEffect
        data object OnWishListClicked: UiEffect
    }
}
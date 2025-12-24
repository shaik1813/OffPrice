package com.apparel.offprice.features.home.presentation.screens.search

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.LOneCategoryItem

interface SearchContract : UnidirectionalViewModel
<SearchContract.UiState, SearchContract.UiEvent, SearchContract.UiEffect> {

    data class UiState(
        val query: String = "",
        val lOneCategoryList: List<LOneCategoryItem> = emptyList(),
        val recentSearches: List<String> = emptyList(),
        val trendingSearches: List<String> = emptyList(),
        val searchResults: List<String> = emptyList(),
        val selectedIndex: Int = -1,
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data class OnQueryChanged(val query: String) : UiEvent
        data class OnCategorySelected(val index: Int,val category: LOneCategoryItem) : UiEvent
        data class RemoveRecent(val query: String) : UiEvent
        data class Submit(val query: String) : UiEvent

        data class OnRecentSearched(val query: String): UiEvent

        data object OnCleared: UiEvent
    }

    sealed interface UiEffect {
        object NavigateToHome : UiEffect
        data class NavigateToSearchResult(val productId: String) : UiEffect
        data class ShowError(val message: String) : UiEffect
    }
}

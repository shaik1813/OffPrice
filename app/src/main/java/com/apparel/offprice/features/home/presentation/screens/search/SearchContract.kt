package com.apparel.offprice.features.home.presentation.screens.search

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.LOneCategoryItem
import com.apparel.offprice.features.home.data.model.recentSearch
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import com.apparel.offprice.features.home.data.model.trendingSearch

interface SearchContract : UnidirectionalViewModel
<SearchContract.UiState, SearchContract.UiEvent, SearchContract.UiEffect> {

    data class UiState(
        val query: String = "",
        val selectedCategory: List<LOneCategoryItem> = sampleLOneCategoryItem,
        val recentSearches: List<String> = recentSearch,
        val trendingSearches: List<String> = trendingSearch,
        val searchResults: List<String> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data class OnQueryChanged(val query: String) : UiEvent
        data class OnCategorySelected(val category: LOneCategoryItem) : UiEvent
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

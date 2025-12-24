package com.apparel.offprice.features.home.presentation.screens.categories

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.LOneCategoryItem
import com.apparel.offprice.features.home.data.model.recentSearch
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import com.apparel.offprice.features.home.data.model.trendingSearch
import com.apparel.offprice.features.home.presentation.component.CategoryListItem

interface CategoriesContract : UnidirectionalViewModel
<CategoriesContract.UiState, CategoriesContract.UiEvent, CategoriesContract.UiEffect> {

    data class UiState(
        val query: String = "",
        val selectedCategory: List<LOneCategoryItem> = sampleLOneCategoryItem,
        val recentSearches: List<String> = recentSearch,
        val trendingSearches: List<String> = trendingSearch,
        val searchResults: List<String> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {

        data object NavigateToSearch: UiEvent
        data object NavigateToWishlist: UiEvent

        data class OnQueryChanged(val query: String) : UiEvent
        data class OnCategorySelected(val category: LOneCategoryItem) : UiEvent
        data class RemoveRecent(val query: String) : UiEvent
        data class Submit(val query: String) : UiEvent

        data class OnRecentSearched(val query: String): UiEvent

        data object OnCleared: UiEvent

        data class OnNavigateToSubCategory(val categoryListItem : CategoryListItem) : UiEvent
    }

    sealed interface UiEffect {
        object NavigateToHome : UiEffect
        data class ShowError(val message: String) : UiEffect
        object NavigateToSearch: UiEffect
        object NavigateToWishlist: UiEffect
        object NavigateToSubCategory: UiEffect
    }
}

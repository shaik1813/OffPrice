package com.apparel.offprice.features.home.presentation.screens.categories

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.LOneCategoryItem
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import com.apparel.offprice.features.home.presentation.component.CategoryBannerItem
import com.apparel.offprice.features.home.presentation.component.CategoryListItem

interface CategoriesContract : UnidirectionalViewModel
<CategoriesContract.UiState, CategoriesContract.UiEvent, CategoriesContract.UiEffect> {

    data class UiState(
        val lOneCategoryList: List<LOneCategoryItem> = emptyList(),
        val categoryList: List<CategoryListItem> = emptyList(),
        val bannerList : List<CategoryBannerItem> = emptyList(),
        val selectedIndex: Int = 0,
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {

        data object NavigateToSearch : UiEvent
        data object NavigateToWishlist : UiEvent
        data class OnCategorySelected(val index: Int, val category: LOneCategoryItem) : UiEvent
        data class OnNavigateToSubCategory(val title: String) : UiEvent
    }

    sealed interface UiEffect {
        object NavigateToHome : UiEffect
        data class ShowError(val message: String) : UiEffect
        object NavigateToSearch : UiEffect
        object NavigateToWishlist : UiEffect

        data class NavigateToSubCategory(val title: String) : UiEffect
    }
}

package com.apparel.offprice.features.home.presentation.screens.categories

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.presentation.component.CategoryListItem

interface SubCategoryContract : UnidirectionalViewModel
<SubCategoryContract.UiState, SubCategoryContract.UiEvent, SubCategoryContract.UiEffect> {

    data class UiState(
        val parentCategoryTitle: String = "",
        val subCategories: List<CategoryListItem> = emptyList()
    )

    sealed interface UiEvent {
        object OnBackClicked : UiEvent
        data class OnSubCategoryClicked(val item: CategoryListItem) : UiEvent

        data object NavigateToSearch : UiEvent
        data object NavigateToWishlist : UiEvent
    }

    sealed interface UiEffect {
        object NavigateBack : UiEffect
        data class NavigateToPLP(val categoryId: String) : UiEffect

        object NavigateToSearch : UiEffect
        object NavigateToWishlist : UiEffect
    }
}

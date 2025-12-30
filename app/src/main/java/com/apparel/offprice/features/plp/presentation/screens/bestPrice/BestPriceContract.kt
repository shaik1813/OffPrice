package com.apparel.offprice.features.plp.presentation.screens.bestPrice

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.plp.data.model.LTwoCategoryListItem
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems

interface BestPriceContract : UnidirectionalViewModel<
        BestPriceContract.UiState,
        BestPriceContract.UiEvent,
        BestPriceContract.UiEffect
        >{


    data class UiState(
        val isLoading: Boolean = false,
        val lTwoCategoryList: List<LTwoCategoryListItem> = emptyList(),
        val selectedCategoryId: Int = 1,
        val isFilterSheetOpen : Boolean = false,
        val isSortSheetOpen : Boolean = false,
        val productList: List<ProductCardItems> = emptyList(),
        val showScrollToTop: Boolean = false
    )

    sealed interface UiEvent{
        data object NavigateToSearch: UiEvent
        data object NavigateToWishlist: UiEvent

        data class CategorySelected(val category: LTwoCategoryListItem): UiEvent
        data object ToggleSortSheet : UiEvent
        data object ToggleFilterSheet : UiEvent
        data class WishlistClicked(val product: ProductCardItems) : UiEvent

        data object ScrollToTopClicked : UiEvent
    }

    sealed interface UiEffect{
        data object NavigateToSearch: UiEffect
        data object NavigateToWishlist: UiEffect
        data object ScrollToTop: UiEffect
    }

}

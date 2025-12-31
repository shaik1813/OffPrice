package com.apparel.offprice.features.plp.presentation.screens.bestPrice

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.plp.data.model.FilterGroup
import com.apparel.offprice.features.plp.data.model.FilterItem
import com.apparel.offprice.features.plp.data.model.FilterType
import com.apparel.offprice.features.plp.data.model.LTwoCategoryListItem
import com.apparel.offprice.features.plp.data.model.SortProductItem
import com.apparel.offprice.features.plp.data.model.ProductCardItems
import com.apparel.offprice.features.plp.presentation.screens.plpScreen.PLPContract

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
        val sortList :List<SortProductItem> = emptyList(),
        val filters: List<FilterGroup> = emptyList(),
        val inlineFilters: List<FilterGroup> = emptyList(),
        val selectedFilterType : FilterType = FilterType.BRAND,
        val showScrollToTop: Boolean = false
    )

    sealed interface UiEvent{
        data object NavigateToSearch: UiEvent
        data object NavigateToWishlist: UiEvent

        data class CategorySelected(val category: LTwoCategoryListItem): UiEvent
        data object ToggleSortSheet : UiEvent
        data class FilterSheetOpen(val filterType: FilterType) : UiEvent

        data object FilterSheetClosed : UiEvent
        data class WishlistClicked(val product: ProductCardItems) : UiEvent

        data object ScrollToTopClicked : UiEvent

        data class OnSortSelected(val sortItem: SortProductItem) : UiEvent

        data class OnFilterTypeClicked(val filterType: FilterType) : UiEvent

        data class OnFilterItemClicked(val filterType: FilterType, val itemId: String) : UiEvent

        data class OnInlineFilterItemClicked(val filterType: FilterType, val filterItem: FilterItem) : UiEvent


        data object OnClearAllClicked : UiEvent
    }

    sealed interface UiEffect{
        data object NavigateToSearch: UiEffect
        data object NavigateToWishlist: UiEffect
        data object ScrollToTop: UiEffect
    }

}

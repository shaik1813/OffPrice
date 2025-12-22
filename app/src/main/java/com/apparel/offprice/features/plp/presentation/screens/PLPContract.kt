package com.apparel.offprice.features.plp.presentation.screens

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface PLPContract :
    UnidirectionalViewModel<
            PLPContract.UiState,
            PLPContract.UiEvent,
            PLPContract.UiEffect
            > {


    data class UiState(
        val isLoading: Boolean = false,
        val products: List<ProductCardItems> = emptyList(),
    )

    sealed interface UiEvent {
        data class OnProductClick(val product: ProductCardItems) : UiEvent
        data class OnWishlistClick(val product: ProductCardItems) : UiEvent
        object OnFilterClick : UiEvent
        object OnSortClick : UiEvent
        object OnCloseBottomSheet : UiEvent
    }

    sealed interface UiEffect {
        data class ShowMessage(val message: String) : UiEffect
    }

}
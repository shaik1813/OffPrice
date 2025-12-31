package com.apparel.offprice.features.wishlist.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.plp.data.model.ProductCardItems

interface WishListContract :
    UnidirectionalViewModel<WishListContract.UiState, WishListContract.UiEvent, WishListContract.UiEffect> {

    data class UiState(
        val wishListCount: Int = 0,
        val wishListItems: List<ProductCardItems> = emptyList(),
        val isWishListRemovalDialog : Boolean = false,
        val isAddToBagDialog: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {

        data object OnStartShopping : UiEvent

        data object OnNavigateToCart : UiEvent

        data object OnBackPressed : UiEvent

        data class OnNavigateToPDP(val product: ProductCardItems): UiEvent

        data class OnAddToBagClicked(val product: ProductCardItems) : UiEvent

        data class RemoveWishList(val productId: String) : UiEvent


    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect

        data object OnStartShopping: UiEffect

        data object OnNavigateToCart: UiEffect
        data class OnNavigateToPDP(val product: ProductCardItems) : UiEffect

        data class ShowError(val message: String) : UiEffect
    }

}
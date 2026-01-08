package com.apparel.offprice.features.wishlist.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.pdp.data.model.SizeItem
import com.apparel.offprice.features.plp.data.model.ProductCardItems

interface WishListContract :
    UnidirectionalViewModel<WishListContract.UiState, WishListContract.UiEvent, WishListContract.UiEffect> {

    data class UiState(
        val wishListCount: Int = 0,
        val wishListItems: List<ProductCardItems> = emptyList(),
        val sizeListItem : List<SizeItem> = emptyList(),
        val selectedRemovedProductId: String = "",
        val selectedSizeId: String = "",
        val quantity: Int = 1,
        val isWishListRemovalDialog : Boolean = false,
        val isAddToBagDialog: Boolean = false,
        val successfullyAddedToBag: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {

        data object OnStartShopping : UiEvent

        data object OnNavigateToCart : UiEvent

        data object OnBackPressed : UiEvent

        data class OnNavigateToPDP(val product: ProductCardItems): UiEvent

        data class OnAddToBagClicked(val product: ProductCardItems) : UiEvent

        data class RemoveWishList(val productId: String) : UiEvent

        data class OpenRemoveWishListDialog(val productId: String) : UiEvent

        data object OnDismissDialog : UiEvent

        data class SelectSize(val sizeId: String) : UiEvent
        data object IncreaseQty : UiEvent
        data object DecreaseQty : UiEvent
        data object MoveToBag : UiEvent

    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect

        data object OnStartShopping: UiEffect

        data object OnNavigateToCart: UiEffect
        data class OnNavigateToPDP(val product: ProductCardItems) : UiEffect

        data class ShowError(val message: String) : UiEffect
    }

}
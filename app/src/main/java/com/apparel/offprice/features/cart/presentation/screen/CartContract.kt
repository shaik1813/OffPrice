package com.apparel.offprice.features.cart.presentation.screen


import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.cart.data.CartProductItems
import com.apparel.offprice.features.pdp.presentation.screen.PDPContract

interface CartContract : UnidirectionalViewModel
<CartContract.UiState, CartContract.UiEvent, CartContract.UiEffect> {

    data class UiState(
        val isOpenDialog: Boolean = false,
        val isCouponOfferSheet: Boolean = false,
        val isDeleteCartDialog: Boolean = false,
        val isCheckedClub: Boolean = false,
        val isCheckedStore: Boolean = false,
        val isQuantitySheet: Boolean = false,
        val isOfferDialog: Boolean = false,
        val couponCode: String = "WELCOME",
        val isApplied: Boolean = false,
        val isOpenShipFee: Boolean = false,
        val isCartEmpty: Boolean = false,
        val cartItems: List<CartProductItems> = emptyList(),
        val deleteItemId: Int? = null,
        val selectedQuantity: Int = 1,
        val selectCartPos: Int = -1
    )

    sealed interface UiEvent {
        data object onOpenBottomSheetOffer : UiEvent
        data object onCloseBottomSheetOffer : UiEvent
        data class onOpenDeleteCartConfirm(val itemId:Int) : UiEvent
        data object onCloseDeleteCartConfirm : UiEvent
        data object OnToggleCheckedClubPoint : UiEvent
        data object OnToggleCheckedStorePoint : UiEvent
        data class OnOpenQuantitySheet(val selectedPos:Int) : UiEvent
        data object OnCloseQuantitySheet : UiEvent
        data class OnCouponChanged(val value: String) : UiEvent
        object OnApplyToggleClick : UiEvent
        data class OnApplyCoupon(val code: String) : UiEvent
        object OnShipFeeClick : UiEvent
        data object OnOpenOfferDetailDialog : UiEvent
        data object OnCloseOfferDetailDialog : UiEvent
        data class OnDeleteCartItem(val itemId:Int) : UiEvent
        data class OnQuantitySelected(val quantity: Int) : UiEvent
        object OnSubmitQuantity : UiEvent
        data class onWishListClicked(val productId:Int) : UiEvent
    }

    sealed interface UiEffect {
        data class ShowMessage(val message: String) : UiEffect
    }
}

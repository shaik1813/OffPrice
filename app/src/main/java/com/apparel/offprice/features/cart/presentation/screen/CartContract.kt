package com.apparel.offprice.features.cart.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.cart.data.CartProductItems

interface CartContract : UnidirectionalViewModel
<CartContract.UiState, CartContract.UiEvent, CartContract.UiEffect> {

    data class UiState(
        var isOpenDialog: Boolean = false,
        var isCouponOfferSheet: Boolean = false,
        var isDeleteCartDialog: Boolean = false,

        var isCheckedClub: Boolean = false,
        var isCheckedStore: Boolean = false,
        var isQuantitySheet: Boolean = false,
        var isOfferDialog: Boolean = false,
        val couponCode: String = "WELCOME",
        val isApplied: Boolean = false,
        var isOpenShipFee: Boolean = false,
        var isCartEmpty: Boolean = false,
        val cartItems: List<CartProductItems> = emptyList(),
        var deleteItemId: Int? = null,
        var selectedQuantity: Int = 1,
        var selectCartPos: Int = -1
    )

    sealed interface UiEvent {
        data object onOpenBottomSheetOffer : UiEvent
        data object onCloseBottomSheetOffer : UiEvent
        data class onOpenDeleteCartConfirm(val itemId:String) : UiEvent
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
    }

    sealed interface UiEffect {
        data class ShowMessage(val message: String) : UiEffect
    }
}

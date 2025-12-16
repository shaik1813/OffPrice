package com.apparel.offprice.features.cart.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface CartContract : UnidirectionalViewModel
<CartContract.UiState, CartContract.UiEvent, CartContract.UiEffect> {

    data class UiState(
        var isOpenDialog: Boolean = false,
        var isCouponOfferSheet: Boolean = false,
        var isDeleteCartDialog: Boolean = false,

        var isCheckedClub: Boolean = false,
        var isCheckedStore: Boolean = false
        )

    sealed interface UiEvent {
        data object onOpenBottomSheetOffer : UiEvent
        data object onCloseBottomSheetOffer : UiEvent
        data object onOpenDeleteCartConfirm : UiEvent
        data object onCloseDeleteCartConfirm : UiEvent
        data object OnToggleCheckedClubPoint : UiEvent
        data object OnToggleCheckedStorePoint : UiEvent
    }

    sealed interface UiEffect {

    }
}

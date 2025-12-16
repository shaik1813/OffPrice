package com.apparel.offprice.features.cart.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList

interface CartContract : UnidirectionalViewModel
<CartContract.UiState, CartContract.UiEvent, CartContract.UiEffect> {

    data class UiState(
        var isOpenDialog: Boolean = false,
        var isCouponOfferSheet: Boolean = false,
        var isDeleteCartDialog: Boolean = false
    )

    sealed interface UiEvent {
        data object onOpenBottomSheetOffer : UiEvent
        data object onCloseBottomSheetOffer : UiEvent
        data object onOpenDeleteCartConfirm : UiEvent
        data object onCloseDeleteCartConfirm : UiEvent


    }

    sealed interface UiEffect {

    }
}

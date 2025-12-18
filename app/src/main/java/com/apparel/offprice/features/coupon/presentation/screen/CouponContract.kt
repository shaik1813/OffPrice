package com.apparel.offprice.features.coupon.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.coupon.data.BankCouponModel
import com.apparel.offprice.features.coupon.data.CouponModel

interface CouponContract :
    UnidirectionalViewModel<
        CouponContract.UiState,
        CouponContract.UiEvent,
        CouponContract.UiEffect > {


    data class UiState(
        val isLoading: Boolean = false,
        val coupons: List<CouponModel> = emptyList(),
        val bankCoupons: List<BankCouponModel> = emptyList(),
        val isTermsAndConditionsDialog: Boolean = false,
    )

    sealed interface UiEvent {
        data object OnBackPressed : UiEvent
        data class OnApplyCoupon(val couponCode: String) : UiEvent
        data class OnApplyBankCoupon(val bankCouponId: String) : UiEvent

        data class OnCopyCode(val couponCode: String) : UiEvent
        data object OnTermsAndConditionsClicked : UiEvent
        data object OnCleared : UiEvent
    }

    sealed interface UiEffect {
        data object OnNavigateBack : UiEffect
        data class ShowError(val message: String) : UiEffect
        data class CopyToClipboard(val text: String) : UiEffect
    }


}
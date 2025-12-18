package com.apparel.offprice.features.coupon.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.coupon.data.BankCouponModel
import com.apparel.offprice.features.coupon.data.CouponModel
import com.apparel.offprice.features.coupon.data.sampleBankCouponList
import com.apparel.offprice.features.coupon.data.sampleCouponList
import com.apparel.offprice.features.coupon.presentation.screen.CouponContract.UiEffect.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CouponViewModel @Inject constructor(

) : ViewModel(), CouponContract {

    private val _state = MutableStateFlow(CouponContract.UiState())
    override val state: StateFlow<CouponContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CouponContract.UiEffect>()
    override val effect: SharedFlow<CouponContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: CouponContract.UiEvent) {
        when (event) {
            is CouponContract.UiEvent.OnApplyBankCoupon -> {

            }

            is CouponContract.UiEvent.OnApplyCoupon -> {

            }

            CouponContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(CouponContract.UiEffect.OnNavigateBack)
                }
            }

            CouponContract.UiEvent.OnCleared -> {
                _state.update { it.copy(isTermsAndConditionsDialog = false) }
            }
            is CouponContract.UiEvent.OnTermsAndConditionsClicked -> {
               _state.update { it.copy(isTermsAndConditionsDialog = true) }
            }

            is CouponContract.UiEvent.OnCopyCode -> {
                viewModelScope.launch {
                    _effect.emit(CouponContract.UiEffect.CopyToClipboard(event.couponCode))
                }
            }
        }
    }

    init {
        initialState()
    }

    private fun initialState() {
        _state.update {
            it.copy(
                coupons = sampleCoupons(),
                bankCoupons = sampleBankCoupons(),
            )
        }
    }

    private fun sampleCoupons(): List<CouponModel> = sampleCouponList

    private fun sampleBankCoupons(): List<BankCouponModel> = sampleBankCouponList
}
package com.apparel.offprice.features.customerSupport.presentation.screens.shippingInfo

import androidx.lifecycle.ViewModel
import com.apparel.offprice.features.customerSupport.presentation.screens.customerSupport.CustomerSupportContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShippingInfoViewModel @Inject constructor(

): ViewModel(), ShippingInfoContract {


    private val _state = MutableStateFlow(ShippingInfoContract.UiState())
    override val state: StateFlow<ShippingInfoContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ShippingInfoContract.UiEffect>()
    override val effect: SharedFlow<ShippingInfoContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: ShippingInfoContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
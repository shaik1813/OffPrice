package com.apparel.offprice.features.customerSupport.presentation.screens.returnPolicy

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.customerSupport.presentation.screens.disclaimer.DisclaimerContract

interface ReturnPolicyContract: UnidirectionalViewModel<
        ReturnPolicyContract.UiState,
        ReturnPolicyContract.UiEvent,
        ReturnPolicyContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false,
        val returnPolicyText:String = ""
    )

    sealed interface UiEvent {
        data object LoadReturnPolicy : UiEvent
    }

    sealed interface UiEffect {

    }

}
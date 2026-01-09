package com.apparel.offprice.features.customerSupport.presentation.screens.returnPolicy

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface ReturnPolicyContract: UnidirectionalViewModel<
        ReturnPolicyContract.UiState,
        ReturnPolicyContract.UiEvent,
        ReturnPolicyContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
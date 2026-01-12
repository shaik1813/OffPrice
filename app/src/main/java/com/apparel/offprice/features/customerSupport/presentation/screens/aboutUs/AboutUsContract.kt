package com.apparel.offprice.features.customerSupport.presentation.screens.aboutUs

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface AboutUsContract: UnidirectionalViewModel<
        AboutUsContract.UiState,
        AboutUsContract.UiEvent,
        AboutUsContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
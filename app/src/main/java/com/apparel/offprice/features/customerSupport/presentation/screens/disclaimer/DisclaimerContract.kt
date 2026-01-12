package com.apparel.offprice.features.customerSupport.presentation.screens.disclaimer

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface DisclaimerContract: UnidirectionalViewModel<
        DisclaimerContract.UiState,
        DisclaimerContract.UiEvent,
        DisclaimerContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
package com.apparel.offprice.features.customerSupport.presentation.screens.termsAndCondition

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface TermsAndConditionContract: UnidirectionalViewModel<
        TermsAndConditionContract.UiState,
        TermsAndConditionContract.UiEvent,
        TermsAndConditionContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
package com.apparel.offprice.features.customerSupport.presentation.screens.termsAndCondition

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface TermsAndConditionContract: UnidirectionalViewModel<
        TermsAndConditionContract.UiState,
        TermsAndConditionContract.UiEvent,
        TermsAndConditionContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false,
        val termsAndConditionText:String = ""
    )

    sealed interface UiEvent {
        data object LoadTermsCondition : UiEvent
    }

    sealed interface UiEffect {

    }

}
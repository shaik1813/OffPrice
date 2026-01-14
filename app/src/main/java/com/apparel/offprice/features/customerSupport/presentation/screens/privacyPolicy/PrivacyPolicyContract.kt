package com.apparel.offprice.features.customerSupport.presentation.screens.privacyPolicy

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.customerSupport.presentation.screens.termsAndCondition.TermsAndConditionContract

interface PrivacyPolicyContract: UnidirectionalViewModel<
        PrivacyPolicyContract.UiState,
        PrivacyPolicyContract.UiEvent,
        PrivacyPolicyContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false,
        val privacyAndPolicyText:String = ""
    )

    sealed interface UiEvent {
        data object LoadPrivacyPolicy: UiEvent
    }

    sealed interface UiEffect {

    }

}
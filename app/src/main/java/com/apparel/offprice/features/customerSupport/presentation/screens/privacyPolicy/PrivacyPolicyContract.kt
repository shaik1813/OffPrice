package com.apparel.offprice.features.customerSupport.presentation.screens.privacyPolicy

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface PrivacyPolicyContract: UnidirectionalViewModel<
        PrivacyPolicyContract.UiState,
        PrivacyPolicyContract.UiEvent,
        PrivacyPolicyContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
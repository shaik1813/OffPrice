package com.apparel.offprice.features.authentication.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel


interface ForgotContract : UnidirectionalViewModel
<ForgotContract.UiState, ForgotContract.UiEvent, ForgotContract.UiEffect> {

    data class UiState(
        val email: String = "",
        val emailError: String? = null,
    )

    sealed interface UiEvent {
        object OnNavigateBack : UiEvent
        data class OnValueChangeEmail(val value: String) : UiEvent
    }

    sealed interface UiEffect {
    }
}
package com.apparel.offprice.features.authentication.presentation.component

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.routes.AppScreen

interface LoginEmptyContract :
    UnidirectionalViewModel<LoginEmptyContract.EmptyUiState, LoginEmptyContract.EmptyUiEvent, LoginEmptyContract.EmptyUiEffect> {

    data class EmptyUiState(
        val isLoginVisible: Boolean = false,
    )

    sealed interface EmptyUiEvent {
        object OnLoginClick : EmptyUiEvent
        object OnCloseLogin : EmptyUiEvent
        object OnNavigateBack : EmptyUiEvent
        data class OnNavigate (val screen: AppScreen) : EmptyUiEvent

    }

    sealed interface EmptyUiEffect {
        data object OnNavigateBack : EmptyUiEffect
        data class Navigate2(val screen: AppScreen) : EmptyUiEffect

    }
}
package com.apparel.offprice.features.home.presentation.screens.home

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface HomeContract :
    UnidirectionalViewModel<HomeContract.UiState, HomeContract.UiEvent, HomeContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {}

    sealed interface UiEffect {}
}
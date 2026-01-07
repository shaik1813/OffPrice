package com.apparel.offprice.features.storeLocator.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface StoreLocatorContract: UnidirectionalViewModel<
        StoreLocatorContract.UiState,
        StoreLocatorContract.UiEvent,
        StoreLocatorContract.UiEffect
        > {


    data class UiState(
        val isLoading: Boolean = false
    )


    sealed interface UiEvent{

    }

    sealed interface UiEffect{

    }
}
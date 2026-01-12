package com.apparel.offprice.features.customerSupport.presentation.screens.customerSupport

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.profile.data.MyAccountItems
import com.apparel.offprice.routes.AppScreen

interface CustomerSupportContract: UnidirectionalViewModel<
        CustomerSupportContract.UiState,
        CustomerSupportContract.UiEvent,
        CustomerSupportContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false,
        val customerSupportItem : List<MyAccountItems> = emptyList(),
    )

    sealed interface UiEvent {
        data class AccountItemClick(val item: AppScreen) : UiEvent
    }

    sealed interface UiEffect {
        data class AccountItemClick(val item: AppScreen): UiEffect
    }

}
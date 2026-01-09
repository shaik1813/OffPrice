package com.apparel.offprice.features.customerSupport.presentation.screens.contactInquiry

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface ContactInquiryContract: UnidirectionalViewModel<
        ContactInquiryContract.UiState,
        ContactInquiryContract.UiEvent,
        ContactInquiryContract.UiEffect
        > {

    data class UiState(
        val isLoading : Boolean = false
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
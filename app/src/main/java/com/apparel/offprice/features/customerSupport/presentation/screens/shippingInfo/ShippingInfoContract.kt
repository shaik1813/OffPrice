package com.apparel.offprice.features.customerSupport.presentation.screens.shippingInfo

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.customerSupport.presentation.screens.returnPolicy.ReturnPolicyContract

interface ShippingInfoContract: UnidirectionalViewModel<
        ShippingInfoContract.UiState,
        ShippingInfoContract.UiEvent,
        ShippingInfoContract.UiEffect
        > {

    data class UiState(
        val isLoading: Boolean = false,
        val faqs: List<FAQData> = emptyList(),
        val expandedFaqId: Int? = null,
        val shippingText:String = ""
    )

    sealed interface UiEvent {
        data class ToggleFaqExpanded(val faqId: Int) : UiEvent
        data object LoadShippingInfo : ShippingInfoContract.UiEvent
    }

    sealed interface UiEffect {

    }

}

data class FAQData(
    val id: Int,
    val question: String,
    val answer: String
)
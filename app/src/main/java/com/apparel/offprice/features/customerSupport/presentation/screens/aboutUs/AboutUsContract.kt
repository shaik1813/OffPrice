package com.apparel.offprice.features.customerSupport.presentation.screens.aboutUs

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.customerSupport.data.Brand
import com.apparel.offprice.features.customerSupport.data.FeatureCardModel

interface AboutUsContract : UnidirectionalViewModel<
        AboutUsContract.UiState,
        AboutUsContract.UiEvent,
        AboutUsContract.UiEffect
        > {

    data class UiState(
        val isLoading: Boolean = false,
        val brands: List<Brand> = emptyList(),
        val featureCards: List<FeatureCardModel> = emptyList(),
        val aboutUsText : String = ""
    )

    sealed interface UiEvent {

    }

    sealed interface UiEffect {

    }

}
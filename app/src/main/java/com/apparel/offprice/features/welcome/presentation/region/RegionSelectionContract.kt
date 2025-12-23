package com.apparel.offprice.features.welcome.presentation.region

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.countryList

interface RegionSelectionContract: UnidirectionalViewModel<
        RegionSelectionContract.UiState,
        RegionSelectionContract.UiEvent,
        RegionSelectionContract.UiEffect
    > {

    data class UiState(
        val isLoading: Boolean = false,
        val regionList: List<Country> = emptyList()
    )

    sealed interface UiEvent{
        data class OnRegionSelected(val country: Country): UiEvent
    }

    sealed interface UiEffect{
        data object NavigateToNextScreen: UiEffect
    }
}
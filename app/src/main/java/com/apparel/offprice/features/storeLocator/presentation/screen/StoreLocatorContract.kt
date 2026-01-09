package com.apparel.offprice.features.storeLocator.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.storeLocator.data.CountryStoreList
import com.apparel.offprice.features.storeLocator.data.StoreListModel

interface StoreLocatorContract : UnidirectionalViewModel<
        StoreLocatorContract.UiState, StoreLocatorContract.UiEvent, StoreLocatorContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = false,
        val isListOfStore: Boolean = false,
        val listOfCountry: List<CountryStoreList> = emptyList(),
        val selectedCountryStore: CountryStoreList? = null,
        val country : String = "",
        val city: String = "",
        val area: String = "",
    )

    sealed interface UiEvent {
        data class OnCountrySelected(val country: CountryStoreList) : UiEvent
        data class OnCallStore(val phone: String) : UiEvent
        data class OnStoreDirection(val store: StoreListModel) : UiEvent
        data object OnBackPressed : UiEvent
        data object ToggleStoreView: UiEvent

        data class OnCountryChanged(val value: String) : UiEvent

        data class OnCityChanged(val value: String) : UiEvent

        data class OnAreaChanged(val value: String) : UiEvent

    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect
        data class OpenDialer(val phone: String) : UiEffect
        data class OpenNavigation(val lat: Double, val lng: Double) : UiEffect
    }
}
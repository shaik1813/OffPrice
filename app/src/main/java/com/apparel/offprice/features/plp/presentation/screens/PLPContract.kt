package com.apparel.offprice.features.plp.presentation.screens

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.MyAccountItems
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList

interface PLPContract : UnidirectionalViewModel
<PLPContract.UiState, PLPContract.UiEvent, PLPContract.UiEffect> {

    data class UiState(
        val isGuestUser: Boolean = false,
        val countryItemList: List<Country> = countryList,
        val languageItemList : List<Language> = languageList,
        val countrySelected : Country = countryItemList.first(),
        val languageSelected : Language = languageItemList.first(),
        val username : String = "Jack Harrington",
        val userEmail: String = "Jackharrington21@gmail.com",
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data class OnCountrySelected(val country: Country) : UiEvent

        data class OnLanguageSelected(val language: Language) : UiEvent

        data object NavigateToSearch : UiEvent

        data object NavigateToWishlist : UiEvent

        data object NavigateToLogin : UiEvent

        data object NavigateToRegistration : UiEvent

        data class AccountItemClick(val item: MyAccountItems) : UiEvent
        data object OnCleared : UiEvent
    }

    sealed interface UiEffect {

        object NavigateToSearch : UiEffect
        object NavigateToWishlist : UiEffect
        object NavigateToLogin: UiEffect
        object NavigateToRegistration : UiEffect
        data class AccountItemClick(val item: MyAccountItems): UiEffect
        data class ShowError(val message: String) : UiEffect
    }
}

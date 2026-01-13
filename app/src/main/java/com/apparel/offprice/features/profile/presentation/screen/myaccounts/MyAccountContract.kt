package com.apparel.offprice.features.profile.presentation.screen.myaccounts

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList
import com.apparel.offprice.features.profile.data.MyAccountItems
import com.apparel.offprice.routes.AppScreen

interface MyAccountContract : UnidirectionalViewModel
<MyAccountContract.UiState, MyAccountContract.UiEvent, MyAccountContract.UiEffect> {

    data class UiState(
        val isGuestUser: Boolean = false,
        val countryItemList: List<Country> = emptyList(),
        val isCountryBottomSheetOpened : Boolean = false,
        val languageItemList : List<Language> = emptyList(),
        val isLanguageBottomSheetOpened : Boolean = false,
        val countrySelected : Country = countryList.first(),
        val languageSelected : Language = languageList.first(),
        val username : String = "",
        val userEmail: String = "",
        val accountSettingItem : List<MyAccountItems> = emptyList(),
        val myShoppingItem : List<MyAccountItems> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data class OnCountrySelected(val country: Country) : UiEvent

        data class OnLanguageSelected(val language: Language) : UiEvent

        data object NavigateToSearch : UiEvent

        data object NavigateToWishlist : UiEvent

        data object NavigateToLogin : UiEvent

        data object NavigateToRegistration : UiEvent

        data class AccountItemClick(val item: AppScreen) : UiEvent

        data object OnCountryToggle : UiEvent

        data object OnLanguageToggle : UiEvent

        data object OnCleared : UiEvent

        data object Logout : UiEvent

        data object OnScreenEntry : UiEvent
    }

    sealed interface UiEffect {

        object NavigateToSearch : UiEffect
        object NavigateToWishlist : UiEffect
        object NavigateToLogin: UiEffect
        object NavigateToRegistration : UiEffect
        data class AccountItemClick(val item: AppScreen): UiEffect

        data class ShowError(val message: String) : UiEffect
    }
}

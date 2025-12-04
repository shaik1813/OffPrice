package com.apparel.offprice.features.pdp.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList

interface PDPContract : UnidirectionalViewModel
<PDPContract.UiState, PDPContract.UiEvent, PDPContract.UiEffect> {

    data class UiState(
        var isOpenDialog: Boolean = false,
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
        data object onOpenBottomSheetLocation : UiEvent
        data object onCloseBottomSheetLocation : UiEvent
    }

    sealed interface UiEffect {

        object onOpenBottomSheetLocation : UiEffect
        object onCloseBottomSheetLocation : UiEffect

        /* data class AccountItemClick(val item: MyAccountItems): UiEffect
         data class ShowError(val message: String) : UiEffect*/
    }
}

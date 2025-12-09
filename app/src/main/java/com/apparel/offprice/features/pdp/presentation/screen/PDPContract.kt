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
        var isAddBasketSheet: Boolean = false,
        var isSizeSelectSheet: Boolean = false,
        var isShareProductSheet: Boolean = false,
        val countryItemList: List<Country> = countryList,
        val languageItemList: List<Language> = languageList,
        val countrySelected: Country = countryItemList.first(),
        val languageSelected: Language = languageItemList.first(),
        val username: String = "Jack Harrington",
        val userEmail: String = "Jackharrington21@gmail.com",
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data object onOpenBottomSheetLocation : UiEvent
        data object onCloseBottomSheetLocation : UiEvent
        data object onOpenAddToBagSheet : UiEvent
        data object onCloseAddToBagSheet : UiEvent
        data object onOpenSizeSelectSheet : UiEvent
        data object onCloseSizeSelectSheet : UiEvent
        data object onOpenShareProductSheet : UiEvent
        data object onCloseShareProductSheet : UiEvent

    }

    sealed interface UiEffect {

        object onOpenBottomSheetLocation : UiEffect
        object onCloseBottomSheetLocation : UiEffect

    }
}

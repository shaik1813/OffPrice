package com.apparel.offprice.features.pdp.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList
import com.apparel.offprice.features.pdp.data.model.ProductDetailItem

interface PDPContract : UnidirectionalViewModel
<PDPContract.UiState, PDPContract.UiEvent, PDPContract.UiEffect> {

    data class UiState(
        var isOpenDialog: Boolean = false,
        var isAddBasketSheet: Boolean = false,
        var isSizeSelectSheet: Boolean = false,
        val isOpenLocation: Boolean = false,
        var isShareProductSheet: Boolean = false,
        var isSizeGuideSheet: Boolean = false,
        var isSimilarPLPSheet: Boolean = false,
        var tabbySheet: Boolean = false,
        var tamaraSheet: Boolean = false,
        val selectedCity: String = "",
        val countryItemList: List<Country> = countryList,
        val languageItemList: List<Language> = languageList,
        val countrySelected: Country = countryItemList.first(),
        val languageSelected: Language = languageItemList.first(),
        val username: String = "Jack Harrington",
        val userEmail: String = "Jackharrington21@gmail.com",
        val isLoading: Boolean = false,
        val pdpDetail: ProductDetailItem ?= null,
        val selectedColorImg : Int = -1
        )

    sealed interface UiEvent {
        data object onOpenBottomSheetLocation : UiEvent
        data object onCloseBottomSheetLocation : UiEvent
        data class onCitySelect(val value: String) : UiEvent
        data object onOpenAddToBagSheet : UiEvent
        data object onCloseAddToBagSheet : UiEvent
        data object onOpenSizeSelectSheet : UiEvent
        data object onCloseSizeSelectSheet : UiEvent
        data object onOpenShareProductSheet : UiEvent
        data object onCloseShareProductSheet : UiEvent
        data object onOpenSizeGuideSheet : UiEvent
        data object onCloseSizeGuideSheet : UiEvent
        data object onOpenSimilarProductSheet : UiEvent
        data object onCloseSimilarProductSheet : UiEvent
        data class onChooseColorImg(val value: Int) : UiEvent
        data object onOpenTabbySheet : UiEvent
        data object onCloseTabbySheet : UiEvent
        data object onOpenTamaraSheet : UiEvent
        data object onCloseTamaraSheet : UiEvent
        data class onWishListClicked(val productId:String) : UiEvent
    }

    sealed interface UiEffect {
        object onOpenBottomSheetLocation : UiEffect
        object onCloseBottomSheetLocation : UiEffect
    }
}

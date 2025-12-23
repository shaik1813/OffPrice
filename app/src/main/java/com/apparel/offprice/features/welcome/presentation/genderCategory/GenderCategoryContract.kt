package com.apparel.offprice.features.welcome.presentation.genderCategory

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.welcome.data.model.GenderCategoryItem


interface GenderCategoryContract: UnidirectionalViewModel<
        GenderCategoryContract.UiState,
        GenderCategoryContract.UiEvent,
        GenderCategoryContract.UiEffect
    > {

    data class UiState(
        val isLoading: Boolean = false,
        val genderList: List<GenderCategoryItem> = emptyList()
    )


    sealed interface UiEvent{
        data class OnGenderSelected(val gender: GenderCategoryItem): UiEvent
        data object OnWishListClicked : UiEvent
        data object OnSearchClicked : UiEvent
    }

    sealed interface UiEffect{
        data object OnNavigateToNextScreen: UiEffect
        data object OnNavigateToWishListScreen: UiEffect
        data object OnNavigateToSearchScreen: UiEffect
    }
}
package com.apparel.offprice.features.profile.presentation.screen.profileSize

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface ProfileSizeContract :
    UnidirectionalViewModel<ProfileSizeContract.UiState, ProfileSizeContract.UiEvent, ProfileSizeContract.UiEffect> {

    data class UiState(
        val selectedGender: Gender = Gender.MEN,
        val isPrimary: Boolean = false,
        val selectedClothingSize: List<String> = emptyList(),
        val selectedDenimSize: List<String> = emptyList(),
        val selectedShoeSize: List<String> = emptyList(),
        val clothingSize: List<String> = listOf(
            "XS", "S", "M", "L", "XL", "XXL", "XXXL"
        ),
        val denimSize: List<String> = listOf(
            "26x30", "26x32", "28x30", "28x32", "28x34", "29x30", "29x32", "30x30",
            "30x32", "30x36", "31x30", "31x32", "31x34", "32x30", "32x32", "32x34",
            "32x36", "33x31", "33x33", "33x34", "34x30", "34x34", "34x32", "34x36",
            "36x32", "36x34", "36x36", "38x30", "38x32"
        ),
        val shoeSize: List<String> = listOf(
            "32", "34", "36", "38", "40", "42", "44", "46"
        ),
        val isLoading: Boolean = false
    )

    enum class Gender {
        MEN, WOMEN, KIDS
    }

    sealed interface UiEvent {
        data object OnBackPressed : UiEvent
        data class OnGenderSelected(val gender: Gender) : UiEvent
        data object OnTogglePrimary : UiEvent
        data class OnClothingSizeSelected(val size: String) : UiEvent
        data class OnDenimSizeSelected(val size: String) : UiEvent
        data class OnShoeSizeSelected(val size: String) : UiEvent
        data object OnClearAll : UiEvent
        data object OnSave : UiEvent
    }

    enum class SizeType {
        CLOTHING, DENIM, SHOE
    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect
        data object OnSaveSuccess : UiEffect
        data object OnClearAllSuccess : UiEffect
    }
}


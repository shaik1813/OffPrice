package com.apparel.offprice.features.profile.presentation.screen.userprofile

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface UserProfileContract : UnidirectionalViewModel
<UserProfileContract.UiState, UserProfileContract.UiEvent, UserProfileContract.UiEffect> {

    data class UiState(
        val username: String = "Jack Harrington",
        val userEmail : String = "Jackharrington21@gmail.com",
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {
        data object OnBackPressed : UiEvent
        data object OnPersonalItemClicked : UiEvent
        data object OnMySizeItemClicked: UiEvent
    }

    sealed interface UiEffect {
        data object OnBackPressed : UiEffect
        data object OnPersonalItemClicked : UiEffect
        data object OnMySizeItemClicked: UiEffect
    }

}
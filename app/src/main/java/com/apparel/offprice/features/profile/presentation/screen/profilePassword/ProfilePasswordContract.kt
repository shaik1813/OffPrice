package com.apparel.offprice.features.profile.presentation.screen.profilePassword

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface ProfilePasswordContract :
    UnidirectionalViewModel<ProfilePasswordContract.UiState, ProfilePasswordContract.UiEvent, ProfilePasswordContract.UiEffect> {

    data class UiState(
        val oldPassword: String = "",
        val newPassword: String = "",
        val confirmPassword: String = "",
        val isOldPasswordVisible: Boolean = false,
        val isNewPasswordVisible: Boolean = false,
        val isConfirmPasswordVisible: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface UiEvent {

        data object ToggleNewPasswordVisibility : UiEvent
        data object ToggleOldPasswordVisibility : UiEvent
        data object ToggleConfirmPasswordVisibility : UiEvent
        data class OnOldPasswordChanged(val oldPass: String) : UiEvent
        data class OnNewPasswordChanged(val newPass: String) : UiEvent
        data class OnConfirmPasswordChanged(val confirmPass: String) : UiEvent
        data object OnChangePasswordClicked : UiEvent

        data object OnCancelChangePasswordClicked : UiEvent
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data class ShowError(val message: String) : UiEffect
        data object ShowPasswordChangedSuccess : UiEffect
    }
}
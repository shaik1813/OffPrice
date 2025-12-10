package com.apparel.offprice.features.profile.presentation.screen.profileDetails

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.countryList

interface ProfileDetailsContract : UnidirectionalViewModel<
        ProfileDetailsContract.UiState, ProfileDetailsContract.UiEvent, ProfileDetailsContract.UiEffect> {

    data class UiState(
        val isEditing: Boolean = false,
        val isPasswordVisible: Boolean = false,
        val isCountryPickerOpen: Boolean = false,
        val showDatePicker: Boolean = false,

        val name: String = "",
        val email: String = "",
        val phoneCode: Country = countryList.first(),
        val phoneNumber: String = "",
        val dob: String = "",
        val genderList: List<String> = listOf("Men","Women","Other"),
        val gender: String = genderList.first(),
        val password: String = "",

        val nameError: String? = null,
        val emailError: String? = null,
        val phoneError: String? = null,
        val dobError: String? = null,
        val genderError: String? = null
    )

    sealed interface UiEvent {
        object ToggleEdit : UiEvent
        object ToggleDatePicker : UiEvent
        data class OnDateSelected(val date: Long?) : UiEvent

        data class EnabledEditing(val isEditing: Boolean): UiEvent

        data class SelectCountry(val country: Country) : UiEvent

        data class OnNameChange(val value: String) : UiEvent
        data class OnEmailChange(val value: String) : UiEvent
        data class OnPhoneChange(val value: String) : UiEvent
        data class OnDobChange(val value: String) : UiEvent
        data class OnGenderChange(val value: String) : UiEvent

        object TogglePasswordVisibility : UiEvent
        object ChangePasswordClick : UiEvent

        object OnBackPressed : UiEvent
    }

    sealed interface UiEffect {
        data object OnNavigateToBack : UiEffect
        data object OnNavigateToChangePassword : UiEffect
    }
}
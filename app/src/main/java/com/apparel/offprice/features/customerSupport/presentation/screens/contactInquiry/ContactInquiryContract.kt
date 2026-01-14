package com.apparel.offprice.features.customerSupport.presentation.screens.contactInquiry

import com.apparel.offprice.common.utils.UnidirectionalViewModel

interface ContactInquiryContract: UnidirectionalViewModel<
        ContactInquiryContract.UiState,
        ContactInquiryContract.UiEvent,
        ContactInquiryContract.UiEffect
        > {

    data class UiState(
        val isLoading: Boolean = false,
        val name: String = "",
        val phoneNumber: String = "",
        val phoneCountryCode: String = "+971",
        val emailAddress: String = "",
        val selectedGender: String = "",
        val genderOptions: List<GenderOption> = emptyList(),
        val subject: String = "",
        val message: String = "",
        val isFormValid: Boolean = false,
        val isSubmitting: Boolean = false,
        val errorMessage: String? = null,
        val successMessage: String? = null
    )

    sealed interface UiEvent {
        data class OnNameChanged(val name: String) : UiEvent
        data class OnPhoneNumberChanged(val phone: String) : UiEvent
        data class OnCountryCodeChanged(val code: String) : UiEvent
        data class OnEmailChanged(val email: String) : UiEvent
        data class OnGenderSelected(val gender: String) : UiEvent
        data class OnSubjectChanged(val subject: String) : UiEvent
        data class OnMessageChanged(val message: String) : UiEvent
        object OnSubmitForm : UiEvent
        object OnClearForm : UiEvent
    }

    sealed interface UiEffect {
        data class ShowError(val message: String) : UiEffect
        data class ShowSuccess(val message: String) : UiEffect
        object FormSubmittedSuccessfully : UiEffect
    }

}

data class GenderOption(
    val id: Int,
    val label: String
)

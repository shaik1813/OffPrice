package com.apparel.offprice.features.customerSupport.presentation.screens.contactInquiry

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ContactInquiryViewModel @Inject constructor(

): ViewModel(), ContactInquiryContract {


    private val _state = MutableStateFlow(ContactInquiryContract.UiState())
    override val state: StateFlow<ContactInquiryContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ContactInquiryContract.UiEffect>()
    override val effect: SharedFlow<ContactInquiryContract.UiEffect> = _effect.asSharedFlow()

    init {
        initializeForm()
    }
    private fun initializeForm() {
        val genderOptions = listOf(
            GenderOption(id = 1, label = "Thank You"),
            GenderOption(id = 2, label = "Male"),
            GenderOption(id = 3, label = "Female"),
            GenderOption(id = 4, label = "Other")
        )

        _state.value = _state.value.copy(
            genderOptions = genderOptions,
            phoneCountryCode = "+971"
        )
    }
    override fun event(event: ContactInquiryContract.UiEvent) {
        when (event) {
            is ContactInquiryContract.UiEvent.OnNameChanged -> {
                _state.value = _state.value.copy(name = event.name)
                validateForm()
            }

            is ContactInquiryContract.UiEvent.OnPhoneNumberChanged -> {
                _state.value = _state.value.copy(phoneNumber = event.phone)
                validateForm()
            }

            is ContactInquiryContract.UiEvent.OnCountryCodeChanged -> {
                _state.value = _state.value.copy(phoneCountryCode = event.code)
                validateForm()
            }

            is ContactInquiryContract.UiEvent.OnEmailChanged -> {
                _state.value = _state.value.copy(emailAddress = event.email)
                validateForm()
            }

            is ContactInquiryContract.UiEvent.OnGenderSelected -> {
                _state.value = _state.value.copy(selectedGender = event.gender)
                validateForm()
            }

            is ContactInquiryContract.UiEvent.OnSubjectChanged -> {
                _state.value = _state.value.copy(subject = event.subject)
                validateForm()
            }

            is ContactInquiryContract.UiEvent.OnMessageChanged -> {
                _state.value = _state.value.copy(message = event.message)
                validateForm()
            }

            ContactInquiryContract.UiEvent.OnSubmitForm -> {
                submitForm()
            }

            ContactInquiryContract.UiEvent.OnClearForm -> {
                clearForm()
            }
        }
    }
    private fun validateForm() {
        val isValid = _state.value.name.isNotBlank() &&
                _state.value.subject.isNotBlank() &&
                isValidEmail(_state.value.emailAddress)

        _state.value = _state.value.copy(isFormValid = isValid)
    }

    private fun isValidEmail(email: String): Boolean {
        return if (email.isBlank()) true
        else email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))
    }

    private fun submitForm() {
        if (!_state.value.isFormValid) {
            return
        }

        _state.value = _state.value.copy(isSubmitting = true)

        try {
            _state.value = _state.value.copy(isSubmitting = false)
            clearForm()
        } catch (_: Exception) {
            _state.value = _state.value.copy(isSubmitting = false)
        }
    }

    private fun clearForm() {
        _state.value = ContactInquiryContract.UiState(
            genderOptions = _state.value.genderOptions,
            phoneCountryCode = "+971"
        )
    }
}
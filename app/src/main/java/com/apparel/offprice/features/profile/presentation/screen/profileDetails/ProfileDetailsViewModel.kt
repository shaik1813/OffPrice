package com.apparel.offprice.features.profile.presentation.screen.profileDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModel @Inject constructor(

): ViewModel(), ProfileDetailsContract {
    private val _state = MutableStateFlow(ProfileDetailsContract.UiState())
    override val state: StateFlow<ProfileDetailsContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileDetailsContract.UiEffect>()
    override val effect: SharedFlow<ProfileDetailsContract.UiEffect> = _effect.asSharedFlow()

    init {
        loadInitialData()
    }


    override fun event(event: ProfileDetailsContract.UiEvent) {
       when(event){
           ProfileDetailsContract.UiEvent.ToggleEdit -> _state.update {
               val validated = validateFields(it)
               if (validated.hasErrors()) validated.copy(isEditing = true)
               else it.copy(isEditing = !it.isEditing)
           }

           ProfileDetailsContract.UiEvent.ToggleCountryPicker -> _state.update {
               it.copy(isCountryPickerOpen = !it.isCountryPickerOpen)
           }

           is ProfileDetailsContract.UiEvent.SelectCountry -> _state.update {
               it.copy(phoneCode = event.country, isCountryPickerOpen = false)
           }

           is ProfileDetailsContract.UiEvent.OnNameChange -> _state.update {
               it.copy(name = event.value, nameError = if (event.value.isBlank()) "Name required" else null)
           }

           is ProfileDetailsContract.UiEvent.OnEmailChange -> _state.update {
               it.copy(
                   email = event.value,
                   emailError = if (!event.value.contains("@")) "Invalid email" else null
               )
           }

           is ProfileDetailsContract.UiEvent.OnPhoneChange -> _state.update {
               it.copy(
                   phoneNumber = event.value,
                   phoneError = if (!event.value.matches(Regex("\\d+"))) "Digits only" else null
               )
           }

           is ProfileDetailsContract.UiEvent.OnDobChange -> _state.update {
               it.copy(dob = event.value, dobError = if (event.value.isBlank()) "Required" else null)
           }

           is ProfileDetailsContract.UiEvent.OnGenderChange -> _state.update {
               it.copy(gender = event.value, genderError = if (event.value.isBlank()) "Required" else null)
           }

           ProfileDetailsContract.UiEvent.TogglePasswordVisibility -> _state.update {
               it.copy(isPasswordVisible = !it.isPasswordVisible)
           }


           ProfileDetailsContract.UiEvent.ChangePasswordClick -> {
               viewModelScope.launch {
                   _effect.emit(ProfileDetailsContract.UiEffect.OnNavigateToChangePassword)
               }
           }

           ProfileDetailsContract.UiEvent.OnBackPressed -> {
               viewModelScope.launch {
                   _effect.emit(ProfileDetailsContract.UiEffect.OnNavigateToBack)
               }
           }

           is ProfileDetailsContract.UiEvent.EnabledEditing -> {
               _state.update { it.copy(isEditing = event.isEditing) }
           }
       }
    }

    private fun loadInitialData(){
        _state.update { it.copy(
            name = "John Doe",
            email = "Johndoe@example.com",
            phoneNumber = "1234567890",
            dob = "1990-01-01",
            gender = "Male",
            password = "password123"
        )}
    }

    private fun validateFields(state: ProfileDetailsContract.UiState): ProfileDetailsContract.UiState {
        return state.copy(
            nameError = if (state.name.isBlank()) "Name cannot be empty" else null,
            emailError = if (!state.email.contains("@")) "Invalid Email" else null,
            phoneError = if (!state.phoneNumber.matches(Regex("\\d+"))) "Invalid Number" else null,
            dobError = if (state.dob.isBlank()) "Required" else null,
            genderError = if (state.gender.isBlank()) "Required" else null
        )
    }

    private fun ProfileDetailsContract.UiState.hasErrors() =
        listOf(nameError, emailError, phoneError, dobError, genderError).any { it != null }
}
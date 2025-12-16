package com.apparel.offprice.features.profile.presentation.screen.profileSize

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
class ProfileSizeViewModel @Inject constructor(

) : ViewModel(), ProfileSizeContract {

    private val _state = MutableStateFlow(ProfileSizeContract.UiState())
    override val state: StateFlow<ProfileSizeContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ProfileSizeContract.UiEffect>()
    override val effect: SharedFlow<ProfileSizeContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: ProfileSizeContract.UiEvent) {
        when (event) {
            is ProfileSizeContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(ProfileSizeContract.UiEffect.OnBackPressed)
                }
            }

            is ProfileSizeContract.UiEvent.OnGenderSelected -> {
                _state.update { it.copy(selectedGender = event.gender) }
            }

            is ProfileSizeContract.UiEvent.OnTogglePrimary -> {
                _state.update { it.copy(isPrimary = !it.isPrimary) }
            }

            is ProfileSizeContract.UiEvent.OnClothingSizeSelected -> {
                _state.update { state ->
                    state.copy(
                        selectedClothingSize = if (state.selectedClothingSize.contains(event.size)) state.selectedClothingSize - event.size else state.selectedClothingSize + event.size
                    )
                }
            }

            is ProfileSizeContract.UiEvent.OnDenimSizeSelected -> {
                _state.update { state ->
                    state.copy(
                        selectedDenimSize = if (state.selectedDenimSize.contains(event.size)) state.selectedDenimSize - event.size else state.selectedDenimSize + event.size
                    )
                }
            }

            is ProfileSizeContract.UiEvent.OnShoeSizeSelected -> {
                _state.update { state ->
                    state.copy(
                        selectedShoeSize = if (state.selectedShoeSize.contains(event.size)) state.selectedShoeSize - event.size else state.selectedShoeSize + event.size
                    )
                }
            }


            is ProfileSizeContract.UiEvent.OnClearAll -> {
                _state.update {
                    it.copy(
                        selectedShoeSize = emptyList(),
                        selectedDenimSize = emptyList(),
                        selectedClothingSize = emptyList(),
                    )
                }
                viewModelScope.launch {
                    _effect.emit(ProfileSizeContract.UiEffect.OnClearAllSuccess)
                }
            }

            is ProfileSizeContract.UiEvent.OnSave -> {
                viewModelScope.launch {
                    // TODO: Save sizes to backend
                    _effect.emit(ProfileSizeContract.UiEffect.OnSaveSuccess)
                }
            }
        }
    }
}
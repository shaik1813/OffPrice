package com.apparel.offprice.features.profile.presentation.screen.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(

) : ViewModel(), UserProfileContract {

    private val _state = MutableStateFlow(UserProfileContract.UiState())
    override val state: StateFlow<UserProfileContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UserProfileContract.UiEffect>()
    override val effect: SharedFlow<UserProfileContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: UserProfileContract.UiEvent) {
        when (event) {
            UserProfileContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(UserProfileContract.UiEffect.OnBackPressed)
                }
            }

            UserProfileContract.UiEvent.OnMySizeItemClicked -> {
                viewModelScope.launch {
                    _effect.emit(UserProfileContract.UiEffect.OnMySizeItemClicked)
                }
            }

            UserProfileContract.UiEvent.OnPersonalItemClicked -> {
                viewModelScope.launch {
                    _effect.emit(UserProfileContract.UiEffect.OnPersonalItemClicked)
                }
            }
        }
    }
}
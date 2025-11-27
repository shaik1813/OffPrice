package com.apparel.offprice.features.home.presentation.screens.myaccounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.languageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val appPreference: AppPreference
) : ViewModel(), MyAccountContract {

    private val _state = MutableStateFlow(MyAccountContract.UiState())
    override val state: StateFlow<MyAccountContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<MyAccountContract.UiEffect>()
    override val effect: SharedFlow<MyAccountContract.UiEffect> = _effectFlow.asSharedFlow()

    init {
        setLanguagePreference()
    }

    private fun setLanguagePreference() {
        viewModelScope.launch {
            _state.update { it -> it.copy(languageSelected = languageList.first { it.localeCode ==  appPreference.languagePreference.first() }) }
        }
    }

    override fun event(event: MyAccountContract.UiEvent) {
        when (event) {
            MyAccountContract.UiEvent.OnCleared -> {

            }

            is MyAccountContract.UiEvent.OnCountrySelected -> {
                _state.update { it.copy(countrySelected = event.country) }
            }

            is MyAccountContract.UiEvent.OnLanguageSelected -> {
                saveLanguagePreference(event.language)
                _state.update { it.copy(languageSelected = event.language) }
            }

            is MyAccountContract.UiEvent.AccountItemClick -> {
                viewModelScope.launch {
                    _effectFlow.emit(MyAccountContract.UiEffect.AccountItemClick(event.item))
                }
            }

            MyAccountContract.UiEvent.NavigateToLogin -> {
                viewModelScope.launch {
                    _effectFlow.emit(MyAccountContract.UiEffect.NavigateToLogin)
                }
            }

            MyAccountContract.UiEvent.NavigateToRegistration -> {
                viewModelScope.launch {
                    _effectFlow.emit(MyAccountContract.UiEffect.NavigateToRegistration)
                }
            }

            MyAccountContract.UiEvent.NavigateToSearch -> {
                viewModelScope.launch {
                    _effectFlow.emit(MyAccountContract.UiEffect.NavigateToSearch)
                }
            }

            MyAccountContract.UiEvent.NavigateToWishlist -> {
                viewModelScope.launch {
                    _effectFlow.emit(MyAccountContract.UiEffect.NavigateToWishlist)
                }
            }
        }
    }

    private fun saveLanguagePreference(language: Language) {
        viewModelScope.launch {
            appPreference.saveLanguagePreference(language.localeCode)
        }
    }

}
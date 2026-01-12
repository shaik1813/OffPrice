package com.apparel.offprice.features.profile.presentation.screen.myaccounts

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList
import com.apparel.offprice.features.profile.data.accountSettingItems
import com.apparel.offprice.features.profile.data.myShoppingItems
import com.apparel.offprice.features.profile.presentation.screen.myaccounts.MyAccountContract.UiEffect.AccountItemClick
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
        initialData()
    }

    private fun setRegionPreference(){
        viewModelScope.launch {
            _state.update { it -> it.copy(countrySelected = countryList.first { it.countryCode ==  appPreference.regionalPreference.first() }) }
        }
    }

    private fun setLanguagePreference() {
        viewModelScope.launch {
            _state.update { it -> it.copy(languageSelected = languageList.first { it.localeCode ==  appPreference.languagePreference.first() }) }
        }
    }

    private fun setLoggedInUser(){
        viewModelScope.launch {
            val isGuestUser = appPreference.isGuestUser.first()
            _state.update { it.copy(isGuestUser = isGuestUser) }
        }
    }

    private fun initialData(){
        _state.update {
            it.copy(
                countryItemList = countryList,
                languageItemList = languageList,
                username = "Jack Harrington",
                userEmail = "Jackharrington21@gmail.com",
                accountSettingItem = accountSettingItems,
                myShoppingItem = myShoppingItems
            )
        }
    }

    override fun event(event: MyAccountContract.UiEvent) {
        when (event) {
            MyAccountContract.UiEvent.OnCleared -> {

            }

            is MyAccountContract.UiEvent.OnCountrySelected -> {
                saveRegionPreference(event.country)
                _state.update { it.copy(countrySelected = event.country , isCountryBottomSheetOpened = false) }
            }

            is MyAccountContract.UiEvent.OnLanguageSelected -> {
                saveLanguagePreference(event.language)
                _state.update { it.copy(languageSelected = event.language , isLanguageBottomSheetOpened = false) }
            }

            is MyAccountContract.UiEvent.AccountItemClick -> {
                viewModelScope.launch {
                    _effectFlow.emit(AccountItemClick(event.item))
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

            MyAccountContract.UiEvent.OnCountryToggle -> {
                _state.update {
                    it.copy(isCountryBottomSheetOpened = !it.isCountryBottomSheetOpened)
                }
            }
            MyAccountContract.UiEvent.OnLanguageToggle -> {
                _state.update {
                    it.copy(isLanguageBottomSheetOpened = !it.isLanguageBottomSheetOpened)
                }
            }

            MyAccountContract.UiEvent.Logout -> {
                viewModelScope.launch {
                    appPreference.saveIsGuestUser(true)
                    _state.update { it.copy(isGuestUser = true) }
                }
            }

            MyAccountContract.UiEvent.OnScreenEntry -> {
                setLoggedInUser()
                setRegionPreference()
                setLanguagePreference()
            }
        }
    }

    private fun saveRegionPreference(country: Country) {
        viewModelScope.launch {
            appPreference.saveRegionalPreference(country.countryCode)
        }
    }

    private fun saveLanguagePreference(language: Language) {
        viewModelScope.launch {
            appPreference.saveLanguagePreference(language.localeCode)
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language.localeCode)
            )
        }
    }

}
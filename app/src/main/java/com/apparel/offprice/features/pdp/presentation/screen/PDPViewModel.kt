package com.apparel.offprice.features.pdp.presentation.screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.Language
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.home.data.model.languageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PDPViewModel @Inject constructor(
    private val appPreference: AppPreference
) : ViewModel(), PDPContract {

    private val _state = MutableStateFlow(PDPContract.UiState())
    override val state: StateFlow<PDPContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<PDPContract.UiEffect>()
    override val effect: SharedFlow<PDPContract.UiEffect> = _effectFlow.asSharedFlow()

    val languagePreference: StateFlow<String?> = appPreference.languagePreference
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    init {
        setRegionPreference()
        setLanguagePreference()
    }

    private fun setRegionPreference() {
        viewModelScope.launch {
            _state.update { it -> it.copy(countrySelected = countryList.first { it.countryCode == appPreference.regionalPreference.first() }) }
        }
    }

    private fun setLanguagePreference() {
        viewModelScope.launch {
            _state.update { it -> it.copy(languageSelected = languageList.first { it.localeCode == appPreference.languagePreference.first() }) }
        }
    }

    override fun event(event: PDPContract.UiEvent) {
        when (event) {

            is PDPContract.UiEvent.OnCountrySelected -> {
                saveRegionPreference(event.country)
                _state.update { it.copy(countrySelected = event.country) }
            }

            PDPContract.UiEvent.onOpenBottomSheetLocation -> {
                viewModelScope.launch {
                    _effectFlow.emit(PDPContract.UiEffect.onOpenBottomSheetLocation)
                }
            }
            PDPContract.UiEvent.onCloseBottomSheetLocation -> {
                viewModelScope.launch {
                    _effectFlow.emit(PDPContract.UiEffect.onCloseBottomSheetLocation)
                }
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
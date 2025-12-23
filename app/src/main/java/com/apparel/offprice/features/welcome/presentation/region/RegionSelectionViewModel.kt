package com.apparel.offprice.features.welcome.presentation.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.countryList
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
class RegionSelectionViewModel @Inject constructor(
    private val appPreference: AppPreference
) : ViewModel(),RegionSelectionContract {

    private val _state = MutableStateFlow(RegionSelectionContract.UiState())
    override val state: StateFlow<RegionSelectionContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegionSelectionContract.UiEffect>()
    override val effect: SharedFlow<RegionSelectionContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: RegionSelectionContract.UiEvent) {
        when(event){
            is RegionSelectionContract.UiEvent.OnRegionSelected -> {
                saveRegion(event.country)
                viewModelScope.launch {
                    _effect.emit(RegionSelectionContract.UiEffect.NavigateToNextScreen)
                }
            }
        }
    }

    init{
        loadRegionSelection()
    }

    private fun loadRegionSelection(){
        _state.update {
            it.copy(
                regionList = countryList
            )
        }
    }

    fun saveRegion(item: Country){
        viewModelScope.launch {
            appPreference.saveRegionalPreference(item.countryCode)
        }
    }

}
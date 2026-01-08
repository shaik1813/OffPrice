package com.apparel.offprice.features.storeLocator.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.storeLocator.data.sampleCountryStoreList
import com.apparel.offprice.features.storeLocator.presentation.screen.StoreLocatorContract.UiEffect.*
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
class StoreLocatorViewModel @Inject constructor(

): ViewModel(), StoreLocatorContract{

    private val _state = MutableStateFlow(StoreLocatorContract.UiState())
    override val state: StateFlow<StoreLocatorContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<StoreLocatorContract.UiEffect>()
    override val effect: SharedFlow<StoreLocatorContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: StoreLocatorContract.UiEvent) {
        when(event){
            StoreLocatorContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(StoreLocatorContract.UiEffect.OnBackPressed)
                }
            }
            is StoreLocatorContract.UiEvent.OnCallStore -> {
                viewModelScope.launch {
                    _effect.emit(OpenDialer(event.phone))
                }
            }
            is StoreLocatorContract.UiEvent.OnCountrySelected -> {
                _state.update {
                    it.copy(
                        selectedCountryStore = event.country
                    )
                }
            }
            is StoreLocatorContract.UiEvent.OnStoreDirection -> {
                viewModelScope.launch {
                    _effect.emit(OpenNavigation(event.store.latitude, event.store.longitude))
                }
            }

            StoreLocatorContract.UiEvent.ToggleStoreView -> {
                _state.update {
                    it.copy(
                        isListOfStore = !it.isListOfStore
                    )
                }
            }

            is StoreLocatorContract.UiEvent.OnAreaChanged -> {
                _state.update {
                    it.copy(
                        area = event.value
                    )
                }
            }
            is StoreLocatorContract.UiEvent.OnCityChanged -> {
                _state.update {
                    it.copy(
                        city = event.value
                    )
                }
            }
            is StoreLocatorContract.UiEvent.OnCountryChanged -> {
                _state.update {
                    it.copy(
                        country = event.value
                    )
                }
            }
        }
    }

    init {
        loadInitialData()
    }

    private fun loadInitialData(){
        _state.update {
            it.copy(
                isListOfStore = false,
                listOfCountry = sampleCountryStoreList,
                selectedCountryStore = sampleCountryStoreList.first()
            )
        }
    }

}
package com.apparel.offprice.features.storeLocator.presentation.screen

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
class StoreLocatorViewModel @Inject constructor(

): ViewModel(), StoreLocatorContract{

    private val _state = MutableStateFlow(StoreLocatorContract.UiState())
    override val state: StateFlow<StoreLocatorContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<StoreLocatorContract.UiEffect>()
    override val effect: SharedFlow<StoreLocatorContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: StoreLocatorContract.UiEvent) {
        when(event){
            else -> {}
        }
    }

}
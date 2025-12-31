package com.apparel.offprice.features.plp.presentation.screens.plpScreen

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
class PLPViewModel @Inject constructor(

): ViewModel(), PLPContract {

    private val _state = MutableStateFlow(PLPContract.UiState())
    override val state: StateFlow<PLPContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PLPContract.UiEffect>()
    override val effect: SharedFlow<PLPContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: PLPContract.UiEvent) {
        when(event){
            PLPContract.UiEvent.OnCloseBottomSheet -> TODO()
            PLPContract.UiEvent.OnFilterClick -> TODO()
            is PLPContract.UiEvent.OnProductClick -> TODO()
            PLPContract.UiEvent.OnSortClick -> TODO()
            is PLPContract.UiEvent.OnWishlistClick -> TODO()
        }
    }
}
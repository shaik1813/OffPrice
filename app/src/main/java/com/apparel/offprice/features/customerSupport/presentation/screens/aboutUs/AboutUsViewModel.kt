package com.apparel.offprice.features.customerSupport.presentation.screens.aboutUs

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
class AboutUsViewModel @Inject constructor(

): ViewModel(), AboutUsContract {


    private val _state = MutableStateFlow(AboutUsContract.UiState())
    override val state: StateFlow<AboutUsContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AboutUsContract.UiEffect>()
    override val effect: SharedFlow<AboutUsContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: AboutUsContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
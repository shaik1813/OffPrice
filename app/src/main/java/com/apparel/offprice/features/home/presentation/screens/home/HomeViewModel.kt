package com.apparel.offprice.features.home.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.apparel.offprice.common.preference.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    appPreference: AppPreference
) : ViewModel(), HomeContract {

    private val _state = MutableStateFlow(HomeContract.UiState())
    override val state: StateFlow<HomeContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.UiEffect>()
    override val effect: SharedFlow<HomeContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: HomeContract.UiEvent) {
        when (event) {

            else -> {}
        }
    }

}
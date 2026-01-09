package com.apparel.offprice.features.customerSupport.presentation.screens.disclaimer

import androidx.lifecycle.ViewModel
import com.apparel.offprice.features.customerSupport.presentation.screens.customerSupport.CustomerSupportContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DisclaimerViewModel @Inject constructor(

): ViewModel(),DisclaimerContract {


    private val _state = MutableStateFlow(DisclaimerContract.UiState())
    override val state: StateFlow<DisclaimerContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DisclaimerContract.UiEffect>()
    override val effect: SharedFlow<DisclaimerContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: DisclaimerContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
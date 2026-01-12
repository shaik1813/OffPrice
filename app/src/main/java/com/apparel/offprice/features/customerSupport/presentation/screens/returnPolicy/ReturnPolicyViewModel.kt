package com.apparel.offprice.features.customerSupport.presentation.screens.returnPolicy

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
class ReturnPolicyViewModel @Inject constructor(

): ViewModel(), ReturnPolicyContract {


    private val _state = MutableStateFlow(ReturnPolicyContract.UiState())
    override val state: StateFlow<ReturnPolicyContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ReturnPolicyContract.UiEffect>()
    override val effect: SharedFlow<ReturnPolicyContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: ReturnPolicyContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
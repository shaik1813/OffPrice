package com.apparel.offprice.features.customerSupport.presentation.screens.privacyPolicy

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
class PrivacyPolicyViewModel @Inject constructor(

): ViewModel(), PrivacyPolicyContract {


    private val _state = MutableStateFlow(PrivacyPolicyContract.UiState())
    override val state: StateFlow<PrivacyPolicyContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PrivacyPolicyContract.UiEffect>()
    override val effect: SharedFlow<PrivacyPolicyContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: PrivacyPolicyContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
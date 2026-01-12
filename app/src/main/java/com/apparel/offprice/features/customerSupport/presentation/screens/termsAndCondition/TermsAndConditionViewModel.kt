package com.apparel.offprice.features.customerSupport.presentation.screens.termsAndCondition

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
class TermsAndConditionViewModel @Inject constructor(

): ViewModel(), TermsAndConditionContract {


    private val _state = MutableStateFlow(TermsAndConditionContract.UiState())
    override val state: StateFlow<TermsAndConditionContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<TermsAndConditionContract.UiEffect>()
    override val effect: SharedFlow<TermsAndConditionContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: TermsAndConditionContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
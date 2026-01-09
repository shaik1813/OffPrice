package com.apparel.offprice.features.customerSupport.presentation.screens.contactInquiry

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
class ContactInquiryViewModel @Inject constructor(

): ViewModel(), ContactInquiryContract {


    private val _state = MutableStateFlow(ContactInquiryContract.UiState())
    override val state: StateFlow<ContactInquiryContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ContactInquiryContract.UiEffect>()
    override val effect: SharedFlow<ContactInquiryContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: ContactInquiryContract.UiEvent) {
        when(event){
            else -> {}
        }
    }
}
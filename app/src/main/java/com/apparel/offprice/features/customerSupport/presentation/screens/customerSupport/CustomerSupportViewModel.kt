package com.apparel.offprice.features.customerSupport.presentation.screens.customerSupport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.customerSupport.data.customerSupportItems
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
class CustomerSupportViewModel @Inject constructor(

): ViewModel(),CustomerSupportContract {


    private val _state = MutableStateFlow(CustomerSupportContract.UiState())
    override val state: StateFlow<CustomerSupportContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CustomerSupportContract.UiEffect>()
    override val effect: SharedFlow<CustomerSupportContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: CustomerSupportContract.UiEvent) {
        when(event){
            is CustomerSupportContract.UiEvent.AccountItemClick -> {
                viewModelScope.launch {
                    _effect.emit(CustomerSupportContract.UiEffect.AccountItemClick(event.item))
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
                customerSupportItem = customerSupportItems
            )
        }
    }
}
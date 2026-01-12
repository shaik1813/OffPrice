package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.returnFlow.data.dummyReturns
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReturnDetailsViewModel @Inject constructor() :
    ViewModel(),
    ReturnDetailsContract {

    private val _state =
        MutableStateFlow(ReturnDetailsContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect =
        MutableSharedFlow<ReturnDetailsContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    fun loadReturnDetails(returnId: String) {
        viewModelScope.launch {
            // TEMP – replace with repository later
            val item = dummyReturns().firstOrNull {
                it.returnId == returnId
            }

            _state.update {
                it.copy(
                    isLoading = false,
                    returnItem = item
                )
            }
        }
    }

    override fun event(event: ReturnDetailsContract.UiEvent) {
        when (event) {
            ReturnDetailsContract.UiEvent.OnBackClick -> {
                viewModelScope.launch {
                    _effect.emit(ReturnDetailsContract.UiEffect.NavigateBack)
                }
            }

            ReturnDetailsContract.UiEvent.OnCleared -> Unit
        }
    }
}

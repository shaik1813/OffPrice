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
class ReturnsViewModel @Inject constructor() : ViewModel(), ReturnsContract {

    private val _state = MutableStateFlow(ReturnsContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ReturnsContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    init {
        loadReturns()
    }

    private fun loadReturns() {
        _state.update {
            it.copy(
                returnsList = dummyReturns() // replace with API later
            )
        }
    }

    override fun event(event: ReturnsContract.UiEvent) {
        when (event) {

            ReturnsContract.UiEvent.OnBackClick -> {
                emitEffect(ReturnsContract.UiEffect.NavigateBack)
            }

            ReturnsContract.UiEvent.OnRequestNewReturnClick -> {
                emitEffect(ReturnsContract.UiEffect.NavigateToNewRequestReturn)
            }

            is ReturnsContract.UiEvent.OnReturnItemClick -> {

                val selectedItem = state.value.returnsList.firstOrNull {
                    it.returnId == event.returnId
                }

                _state.update {
                    it.copy(selectedReturn = selectedItem)
                }

                emitEffect(
                    ReturnsContract.UiEffect.NavigateToReturnDetails(event.returnId)
                )
            }

            ReturnsContract.UiEvent.OnFilterClick -> {
                _state.update {
                    it.copy(isFilterBottomSheetOpen = true)
                }
            }

            is ReturnsContract.UiEvent.OnFilterSelected -> {
                _state.update {
                    it.copy(selectedFilter = event.filter)
                }
            }

            ReturnsContract.UiEvent.OnFilterSubmit -> {
                _state.update {
                    it.copy(isFilterBottomSheetOpen = false)
                }
                loadReturns()
            }

            ReturnsContract.UiEvent.OnCleared -> Unit
        }
    }

    private fun emitEffect(effect: ReturnsContract.UiEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}

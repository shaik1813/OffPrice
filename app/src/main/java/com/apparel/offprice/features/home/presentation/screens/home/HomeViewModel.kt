package com.apparel.offprice.features.home.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            is HomeContract.UiEvent.OnLOneCategoryItemClick -> {
                _state.update { state ->
                    state.copy(
                        lOneCategoryItems = state.lOneCategoryItems.map {
                            it.copy(
                                isSelected = it.id == event.item.id
                            )
                        }
                    )
                }
            }
            HomeContract.UiEvent.OnSearch -> TODO()
            HomeContract.UiEvent.OnWishlist -> TODO()
        }
    }

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        _state.update {
            it.copy(
                lOneCategoryItems = sampleLOneCategoryItem
            )
        }
    }

}
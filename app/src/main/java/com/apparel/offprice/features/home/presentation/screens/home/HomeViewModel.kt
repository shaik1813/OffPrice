package com.apparel.offprice.features.home.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.UserPreferences
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPreferences: UserPreferences
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
                        selectedIndex = event.index
                    )
                }
                saveLevelOneCategory(event.item.id)
            }

            HomeContract.UiEvent.OnSearch -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.UiEffect.OnSearchClicked)
                }
            }

            HomeContract.UiEvent.OnWishlist -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.UiEffect.OnWishListClicked)
                }
            }
        }
    }

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            val index =
                sampleLOneCategoryItem.indexOfFirst { it.id == userPreferences.userGender.first() }
            _state.update {
                it.copy(
                    lOneCategoryList = sampleLOneCategoryItem,
                    selectedIndex = index.coerceAtLeast(0)
                )
            }
            saveLevelOneCategory(sampleLOneCategoryItem[index.coerceAtLeast(0)].id)
        }

    }

    private fun saveLevelOneCategory(id: Int) {
        viewModelScope.launch {
            userPreferences.saveLevelOneCategory(id)
        }
    }
}
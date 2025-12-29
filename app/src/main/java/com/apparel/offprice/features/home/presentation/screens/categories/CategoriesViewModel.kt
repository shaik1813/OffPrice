package com.apparel.offprice.features.home.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class CategoriesViewModel @Inject constructor() : ViewModel(), CategoriesContract {

    private val _state = MutableStateFlow(CategoriesContract.UiState())
    override val state: StateFlow<CategoriesContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<CategoriesContract.UiEffect>()
    override val effect: SharedFlow<CategoriesContract.UiEffect> = _effectFlow.asSharedFlow()

    //Dummy data , replace with the actual API calls
    private val allProducts = listOf(
        "Shirt", "T Shirt", "Polo Shirts", "Night T-Shirts", "Shorts", "Shoes",
        "Cargo Pants", "Luggage", "American Eagle Shirt"
    )


    override fun event(event: CategoriesContract.UiEvent) {
        when (event) {

            is CategoriesContract.UiEvent.OnQueryChanged -> {
                //updateQuery(query = event.query)
            }

            is CategoriesContract.UiEvent.OnCategorySelected -> {
                _state.update { state ->
                    state.copy(
                        selectedIndex = event.index
                    )
                }
            }

            is CategoriesContract.UiEvent.Submit -> {
                //submitSearch(query = event.query)
            }

            is CategoriesContract.UiEvent.RemoveRecent -> {
                //removeRecent(query = event.query)
            }


            CategoriesContract.UiEvent.OnCleared -> {
                viewModelScope.launch {
                    _effectFlow.emit(CategoriesContract.UiEffect.NavigateToHome)
                }
            }

            CategoriesContract.UiEvent.NavigateToSearch -> {
                viewModelScope.launch {
                    _effectFlow.emit(CategoriesContract.UiEffect.NavigateToSearch)
                }
            }

            CategoriesContract.UiEvent.NavigateToWishlist -> {
                viewModelScope.launch {
                    _effectFlow.emit(CategoriesContract.UiEffect.NavigateToWishlist)
                }
            }

            is CategoriesContract.UiEvent.OnNavigateToSubCategory -> {
                viewModelScope.launch {
                    _effectFlow.emit(
                        CategoriesContract.UiEffect.NavigateToSubCategory
                            (title = event.title)
                    )
                }
            }

            is CategoriesContract.UiEvent.OnRecentSearched -> TODO()
        }
    }
}
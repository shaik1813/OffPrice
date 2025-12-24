package com.apparel.offprice.features.home.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.UserPreferences
import com.apparel.offprice.features.home.data.model.recentSearch
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import com.apparel.offprice.features.home.data.model.trendingSearch
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
class SearchViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel(), SearchContract {

    private val _state = MutableStateFlow(SearchContract.UiState())
    override val state: StateFlow<SearchContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<SearchContract.UiEffect>()
    override val effect: SharedFlow<SearchContract.UiEffect> = _effectFlow.asSharedFlow()

    //Dummy data , replace with the actual API calls
    private val allProducts = listOf(
        "Shirt", "T Shirt", "Polo Shirts", "Night T-Shirts", "Shorts", "Shoes",
        "Cargo Pants", "Luggage", "American Eagle Shirt"
    )


    override fun event(event: SearchContract.UiEvent) {
        when (event) {
            is SearchContract.UiEvent.OnQueryChanged -> updateQuery(query = event.query)
            is SearchContract.UiEvent.OnCategorySelected -> {
                _state.update { state ->
                    state.copy(
                        selectedIndex = event.index
                    )
                }
            }

            is SearchContract.UiEvent.Submit -> {
                submitSearch(query = event.query)
            }

            is SearchContract.UiEvent.RemoveRecent -> removeRecent(query = event.query)
            is SearchContract.UiEvent.OnRecentSearched -> {
                viewModelScope.launch {
                    _effectFlow.emit(SearchContract.UiEffect.NavigateToSearchResult(event.query))
                }
            }

            SearchContract.UiEvent.OnCleared -> {
                viewModelScope.launch {
                    _effectFlow.emit(SearchContract.UiEffect.NavigateToHome)
                }
            }
        }
    }

    init {
        loadInitialData()
    }


    private fun loadInitialData() {
        viewModelScope.launch {
            userPreferences.levelOneCategory.collect { id ->
                val index = sampleLOneCategoryItem.indexOfFirst { it.id == id }
                _state.update {
                    it.copy(
                        lOneCategoryList = sampleLOneCategoryItem,
                        recentSearches = recentSearch,
                        trendingSearches = trendingSearch,
                        selectedIndex = index.coerceAtLeast(0)
                    )
                }
            }
        }
    }

    private fun updateQuery(query: String) {
        val filtered = if (query.isBlank()) emptyList() else {
            allProducts.filter { it.contains(query, ignoreCase = true) }
        }
        _state.value = _state.value.copy(query = query, searchResults = filtered)
    }


    private fun submitSearch(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            _state.value = _state.value.copy(
                recentSearches = listOf(query) + _state.value.recentSearches
            )
            _effectFlow.emit(SearchContract.UiEffect.NavigateToSearchResult(query))
        }
    }

    private fun removeRecent(query: String) {
        _state.value = _state.value.copy(
            recentSearches = _state.value.recentSearches.filter { it != query }
        )
    }

}
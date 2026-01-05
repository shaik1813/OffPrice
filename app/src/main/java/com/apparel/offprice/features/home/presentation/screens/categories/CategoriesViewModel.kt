package com.apparel.offprice.features.home.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.UserPreferences
import com.apparel.offprice.features.home.data.model.sampleLOneCategoryItem
import com.apparel.offprice.features.home.presentation.component.sampleCategoryBannerImages
import com.apparel.offprice.features.home.presentation.component.sampleCategoryList
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
class CategoriesViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel(), CategoriesContract {

    private val _state = MutableStateFlow(CategoriesContract.UiState())
    override val state: StateFlow<CategoriesContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<CategoriesContract.UiEffect>()
    override val effect: SharedFlow<CategoriesContract.UiEffect> = _effectFlow.asSharedFlow()


    init {
        initialLoadingData()
    }

    private fun initialLoadingData() {
        viewModelScope.launch {
            val index = userPreferences.levelOneCategory.first()
            _state.update {
                it.copy(
                    lOneCategoryList = sampleLOneCategoryItem,
                    selectedIndex = index,
                    categoryList = sampleCategoryList,
                    bannerList = sampleCategoryBannerImages
                )
            }
        }

    }


    override fun event(event: CategoriesContract.UiEvent) {
        when (event) {

            is CategoriesContract.UiEvent.OnCategorySelected -> {
                _state.update { state ->
                    state.copy(
                        selectedIndex = event.index
                    )
                }
                saveLevelOneCategory(event.index)
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
        }
    }

    private fun saveLevelOneCategory(id: Int) {
        viewModelScope.launch {
            userPreferences.saveLevelOneCategory(id)
        }
    }
}
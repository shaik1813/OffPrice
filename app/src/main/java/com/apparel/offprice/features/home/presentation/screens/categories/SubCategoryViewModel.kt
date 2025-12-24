package com.apparel.offprice.features.home.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.home.presentation.component.CategoryListItem
import com.apparel.offprice.features.home.presentation.component.sampleSubCategoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubCategoryViewModel @Inject constructor() : ViewModel(), SubCategoryContract {

    private val _state = MutableStateFlow(SubCategoryContract.UiState())
    override val state: StateFlow<SubCategoryContract.UiState> = _state

    private val _effect = MutableSharedFlow<SubCategoryContract.UiEffect>()
    override val effect: SharedFlow<SubCategoryContract.UiEffect> = _effect

    fun loadSubCategories(parentCategory: CategoryListItem) {
        _state.update {
            it.copy(
                parentCategoryTitle = parentCategory.title,
                subCategories = sampleSubCategoryList   // replace with API later
            )
        }
    }

    override fun event(event: SubCategoryContract.UiEvent) {
        when (event) {

            SubCategoryContract.UiEvent.NavigateToSearch -> {
                viewModelScope.launch {
                    _effect.emit(SubCategoryContract.UiEffect.NavigateToSearch)
                }
            }
            SubCategoryContract.UiEvent.NavigateToWishlist -> {
                viewModelScope.launch {
                    _effect.emit(SubCategoryContract.UiEffect.NavigateToWishlist)
                }
            }

            SubCategoryContract.UiEvent.OnBackClicked -> {
                viewModelScope.launch {
                    _effect.emit(SubCategoryContract.UiEffect.NavigateBack)
                }
            }

            is SubCategoryContract.UiEvent.OnSubCategoryClicked -> {
                viewModelScope.launch {
                    _effect.emit(
                        SubCategoryContract.UiEffect.NavigateToPLP(event.item.id)
                    )
                }
            }
        }
    }
}

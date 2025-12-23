package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.UserPreferences
import com.apparel.offprice.features.welcome.data.model.GenderCategoryItem
import com.apparel.offprice.features.welcome.data.model.genderCategories
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
class GenderCategoryViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel(), GenderCategoryContract {

    private val _state = MutableStateFlow(GenderCategoryContract.UiState())
    override val state: StateFlow<GenderCategoryContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<GenderCategoryContract.UiEffect>()
    override val effect: SharedFlow<GenderCategoryContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: GenderCategoryContract.UiEvent) {
        when (event) {
            is GenderCategoryContract.UiEvent.OnGenderSelected -> {
                saveGender(event.gender)
                viewModelScope.launch {
                    _effect.emit(GenderCategoryContract.UiEffect.OnNavigateToNextScreen)
                }
            }

            GenderCategoryContract.UiEvent.OnSearchClicked -> {
                viewModelScope.launch {
                    _effect.emit(GenderCategoryContract.UiEffect.OnNavigateToSearchScreen)
                }
            }

            GenderCategoryContract.UiEvent.OnWishListClicked -> {
                viewModelScope.launch {
                    _effect.emit(GenderCategoryContract.UiEffect.OnNavigateToWishListScreen)
                }
            }
        }
    }

    init {
        loadGenderSelection()
    }

    private fun loadGenderSelection() {
        _state.update {
            it.copy(
                genderList = genderCategories
            )
        }
    }


    fun saveGender(gender: GenderCategoryItem) {
        viewModelScope.launch {
            userPreferences.saveGender(gender.id)
        }
    }
}
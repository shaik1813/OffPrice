package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.UserPreferences
import com.apparel.offprice.features.welcome.data.model.GenderCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderCategoryViewModel @Inject constructor(
    private val userPreferences: UserPreferences
): ViewModel() {


    fun saveGender(gender: GenderCategoryItem) {
        viewModelScope.launch {
            userPreferences.saveGender(gender.id)
        }
    }
}
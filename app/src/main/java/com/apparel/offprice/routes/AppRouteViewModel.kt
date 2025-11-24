package com.apparel.offprice.routes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppRouteViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {


    val isDataLoaded = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            combine(
                userPreferences.userLocation,
                userPreferences.userGender
            ) { _, _ -> true }
                .collect {
                    isDataLoaded.value = true
                }
        }
    }

    val userLocation = userPreferences.userLocation.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )

    val userGender = userPreferences.userGender.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null
    )

    fun saveLocation(location: String) {
        viewModelScope.launch {
            userPreferences.saveLocation(location)
        }
    }

    fun saveGender(gender: String) {
        viewModelScope.launch {
            userPreferences.saveGender(gender)
        }
    }
}

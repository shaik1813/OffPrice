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

}

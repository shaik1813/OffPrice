package com.apparel.offprice.features.welcome.presentation.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.home.data.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseLocationViewModel @Inject constructor(
    private val appPreference: AppPreference
) : ViewModel() {

    fun saveRegion(item: Country){
        viewModelScope.launch {
            appPreference.saveRegionalPreference(item.countryCode)
        }
    }

}
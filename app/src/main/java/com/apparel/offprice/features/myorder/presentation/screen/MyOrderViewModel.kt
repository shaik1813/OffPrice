package com.apparel.offprice.features.myorder.presentation.screen

import androidx.lifecycle.ViewModel
import com.apparel.offprice.common.preference.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MyOrderViewModel @Inject constructor(
    private val appPreference: AppPreference
) : ViewModel(), MyOrderContract {

    private val _state = MutableStateFlow(MyOrderContract.UiState())
    override val state: StateFlow<MyOrderContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<MyOrderContract.UiEffect>()
    override val effect: SharedFlow<MyOrderContract.UiEffect> = _effectFlow.asSharedFlow()

    init {
        initialData()
    }

    private fun initialData(){
        _state.update {
            it.copy(
                isGuestUser = false,
            )
        }
    }

    override fun event(event: MyOrderContract.UiEvent) {

    }

}
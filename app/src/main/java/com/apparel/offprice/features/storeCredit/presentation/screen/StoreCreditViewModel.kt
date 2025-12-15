package com.apparel.offprice.features.storeCredit.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.storeCredit.data.CreditType
import com.apparel.offprice.features.storeCredit.data.StoreCreditTransaction
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
class StoreCreditViewModel @Inject constructor(

) : ViewModel(), StoreCreditContract {

    private val _state = MutableStateFlow(StoreCreditContract.UiState())
    override val state: StateFlow<StoreCreditContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<StoreCreditContract.UiEffect>()
    override val effect: SharedFlow<StoreCreditContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: StoreCreditContract.UiEvent) {
        when (event) {
            StoreCreditContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(StoreCreditContract.UiEffect.OnNavigateToBack)
                }
            }

            StoreCreditContract.UiEvent.OnCleared -> TODO()
            is StoreCreditContract.UiEvent.OnFilterSelected -> {
                _state.update { current ->
                    current.copy(selectedFilter = event.filter)
                }
            }
        }
    }

    init {
        initialState()
    }

    private fun initialState() {
        val allTransactions = sampleTransactions()
        val totalReceived = allTransactions
            .filter { it.type == CreditType.RECEIVED }
            .sumOf { it.amount }
        val totalUsed = allTransactions
            .filter { it.type == CreditType.USED }
            .sumOf { it.amount }
        val balance = totalReceived - totalUsed
        _state.update {
            it.copy(
                totalBalance = balance,
                totalReceived = totalReceived,
                totalUsed = totalUsed,
                transactions = allTransactions
            )
        }
    }

    private fun sampleTransactions(): List<StoreCreditTransaction> = listOf(
        StoreCreditTransaction(
            orderId = "#2966605041211671",
            refundedDate = "Jul 20, 2025",
            amount = 10.0,
            type = CreditType.RECEIVED,
        ),
        StoreCreditTransaction(
            orderId = "#2966605041211672",
            refundedDate = "Jul 20, 2025",
            amount = 10.0,
            type = CreditType.USED,
        ),
        StoreCreditTransaction(
            orderId = "#2966605041211673",
            refundedDate = "Jul 20, 2025",
            amount = 10.0,
            type = CreditType.RECEIVED,
        ),
        StoreCreditTransaction(
            orderId = "#2966605041211674",
            refundedDate = "Jul 20, 2025",
            amount = 10.0,
            type = CreditType.RECEIVED,
        ),
        StoreCreditTransaction(
            orderId = "#2966605041211675",
            refundedDate = "Jul 20, 2025",
            amount = 10.0,
            type = CreditType.USED,
        ),
    )
}
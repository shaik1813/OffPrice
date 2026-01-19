package com.apparel.offprice.features.myorder.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.preference.AppPreference
import com.apparel.offprice.features.myorder.data.OrderFilter
import com.apparel.offprice.features.myorder.data.OrderItem
import com.apparel.offprice.features.myorder.data.orderProducts
import com.apparel.offprice.features.myorder.data.orderProducts2
import com.apparel.offprice.features.myorder.data.orderProducts3
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
class MyOrderViewModel @Inject constructor() : ViewModel(), MyOrderContract {

    private val _state = MutableStateFlow(MyOrderContract.UiState())
    override val state: StateFlow<MyOrderContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<MyOrderContract.UiEffect>()
    override val effect: SharedFlow<MyOrderContract.UiEffect> = _effectFlow.asSharedFlow()

    private val allOrders = listOf(
        OrderItem(
            "#296660504211678",
            items = orderProducts,
            orderStatus = "Order Received"
        ),
        OrderItem(
            "#296660504211678",
            items = orderProducts2,
            orderStatus = "Out For Delivery"
        ),
        OrderItem(
            "#296660504211678",
            items = orderProducts3,
            orderStatus = "Shipped"
        )
    )

    init {
        initialData()
    }

    private fun initialData() {
        _state.update {
            it.copy(
                orderData = allOrders
            )
        }
    }

    override fun event(event: MyOrderContract.UiEvent) {
        when (event) {
            is MyOrderContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effectFlow.emit(MyOrderContract.UiEffect.OnBackPressed)
                }
            }

            is MyOrderContract.UiEvent.OnAllOrdersClicked -> {
                _state.update { it.copy(isFilterBottomSheetOpen = true) }
            }

            is MyOrderContract.UiEvent.OnFilterSelected -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedFilter = event.filter,
                        isFilterBottomSheetOpen = false,
                        orderData = filterOrders(event.filter)
                    )
                }
            }

            is MyOrderContract.UiEvent.OnDismissBottomSheet -> {
                _state.update { it.copy(isFilterBottomSheetOpen = false) }
            }

            is MyOrderContract.UiEvent.OnCountrySelected -> {
                // Handle country selection if needed
            }
        }
    }

    private fun filterOrders(filter: OrderFilter): List<OrderItem> {
        return when (filter) {
            OrderFilter.ALL -> allOrders
            OrderFilter.ORDER_RECEIVED -> allOrders.filter { it.orderStatus == "Order Received" }
            OrderFilter.CANCELLED -> allOrders.filter { it.orderStatus == "CANCELLED" }
            OrderFilter.INPROGRESS -> allOrders.filter { it.orderStatus == "Shipped" }
            OrderFilter.DELIVERED -> allOrders.filter { it.orderStatus == "Delivered" }
        }
    }

}
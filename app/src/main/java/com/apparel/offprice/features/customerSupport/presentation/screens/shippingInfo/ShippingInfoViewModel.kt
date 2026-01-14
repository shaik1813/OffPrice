package com.apparel.offprice.features.customerSupport.presentation.screens.shippingInfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShippingInfoViewModel @Inject constructor(

) : ViewModel(), ShippingInfoContract {


    private val _state = MutableStateFlow(ShippingInfoContract.UiState())
    override val state: StateFlow<ShippingInfoContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ShippingInfoContract.UiEffect>()
    override val effect: SharedFlow<ShippingInfoContract.UiEffect> = _effect.asSharedFlow()

    init {
        loadShippingData()
        event(ShippingInfoContract.UiEvent.LoadShippingInfo)
    }

    private fun loadShippingData() {
        val faqList = listOf(
            FAQData(
                id = 1,
                question = "I placed an order today. When can I expect my shipment?",
                answer = "Orders are typically processed within 5-7 business days. Once shipped, you'll receive a tracking number via email."
            ),
            FAQData(
                id = 2,
                question = "What if I miss a delivery?",
                answer = "If you miss a delivery, our team will attempt redelivery. You can also contact support to reschedule."
            ),
            FAQData(
                id = 3,
                question = "I've already paid AED 15, so why am I being charged an additional AED 20?",
                answer = "Additional charges may apply if your order qualifies for different shipping methods or if COD fees are applicable."
            ),
            FAQData(
                id = 4,
                question = "Can I pick up my order from your warehouse?",
                answer = "Warehouse pickup is not currently available. Please contact support to discuss alternative options."
            )
        )

        _state.value = ShippingInfoContract.UiState(
            isLoading = false,
            faqs = faqList
        )
    }

    override fun event(event: ShippingInfoContract.UiEvent) {
        when (event) {

            is ShippingInfoContract.UiEvent.ToggleFaqExpanded -> {
                val currentExpandedId = _state.value.expandedFaqId
                _state.value = _state.value.copy(
                    expandedFaqId = if (currentExpandedId == event.faqId)
                        null
                    else
                        event.faqId
                )
            }

            is ShippingInfoContract.UiEvent.LoadShippingInfo -> {
                loadShippingText()
            }
        }
    }

    private fun loadShippingText() {
        val shippingInfoText = """
<h3>SHIPPING</h3>

<p>
Get information on delivery schedules and shipping charges.
</p>

<p>
All orders are processed within 5-7 business days (excluding weekends and holidays).Shipping is free on orders above AED 99, you will be charged AED 10 if the order value is less than AED 99.AED 20 is applicable on COD orders.
</p>

<h3>DELIVERY CHARGES</h3>

<p>
Your delivery charges will depend on the products you order. These charges will be displayed during
</p>

<p>
the checkout process.
</p>

<p>
Shipping TypeOrder ValueShipping ChargeCash on Delivery Click and CollectAnyFreeNot Available Standard deliveryLess than AED 100AED 15 AED 20Standard deliveryMore than AED 100Free
</p>
"""
        _state.value = _state.value.copy(
            shippingText = shippingInfoText
        )
    }

}
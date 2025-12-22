package com.apparel.offprice.features.checkout.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.features.checkout.presentation.components.AddressForm
import com.apparel.offprice.features.checkout.presentation.components.BottomBar
import com.apparel.offprice.features.checkout.presentation.components.CheckoutProgressStepper
import com.apparel.offprice.features.checkout.presentation.components.DeliveryTypeRow
import com.apparel.offprice.features.checkout.presentation.components.OrderSummarySection
import com.apparel.offprice.features.checkout.presentation.components.PriceBreakdownCard
import com.apparel.offprice.features.checkout.presentation.components.ShippingAddressFilter
import com.apparel.offprice.features.checkout.presentation.components.TopBar

@Composable
fun ShippingAddressScreen(
    onBack: () -> Unit,
    onSaveAddress: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {

        // 🔹 FIXED TOP BAR
        TopBar(onBack = onBack)

        // 🔹 SCROLLABLE CONTENT
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)      // scrolls between top & bottom

        ) {

            // Stepper
            item {
                CheckoutProgressStepper(
                    currentStep = 2
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Delivery / Pickup Toggle
            item { /*DeliveryToggle()*/
                DeliveryTypeRow(
                    selectedFilter = ShippingAddressFilter.DELIVERY,
                    onFilterSelected = {})
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            // Address Form
            item { AddressForm() }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            // Order Summary Section
            item { OrderSummarySection() }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            // Price Breakdown
            item { PriceBreakdownCard() }

            item { Spacer(modifier = Modifier.height(120.dp)) } // avoid overlap with bottom bar
        }

        // 🔹 FIXED BOTTOM BAR
        BottomBar(
            totalAmount = "97.00",
            onSave = onSaveAddress
        )
    }
}




@Preview(showBackground = true)
@Composable
fun ShippingAddressScreenPreview(){
    ShippingAddressScreen(onBack = {}, onSaveAddress = {})
}
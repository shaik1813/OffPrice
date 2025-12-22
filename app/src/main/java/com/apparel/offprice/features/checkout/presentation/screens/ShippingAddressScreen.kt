package com.apparel.offprice.features.checkout.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.plp.data.model.samplePLPHorizontalListItems
import com.apparel.offprice.features.plp.presentation.screens.FilterStrip
import com.apparel.offprice.features.plp.presentation.screens.PLPCategoryHorizontalList
import com.apparel.offprice.features.plp.presentation.screens.PLPScreen
import com.apparel.offprice.features.plp.presentation.screens.ProductGrid
import com.apparel.offprice.features.plp.presentation.screens.sampleProducts
import com.apparel.offprice.features.storeCredit.data.StoreCreditFilter

@Composable
fun ShippingAddressScreen(
    onBack: () -> Unit,
    onSaveAddress: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
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
            item { CheckoutProgressStepper(
                currentStep = 2
            ) }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Delivery / Pickup Toggle
            item { /*DeliveryToggle()*/
            DeliveryTypeRow(selectedFilter = ShippingAddressFilter.DELIVERY, onFilterSelected = {})
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
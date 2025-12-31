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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.cart.data.priceData
import com.apparel.offprice.features.checkout.presentation.components.AddAddressBottomSheet
import com.apparel.offprice.features.checkout.presentation.components.AddressForm
import com.apparel.offprice.features.checkout.presentation.components.AddressSelectionBottomSheet
import com.apparel.offprice.features.checkout.presentation.components.BottomBar
import com.apparel.offprice.features.checkout.presentation.components.CheckoutProgressStepper
import com.apparel.offprice.features.checkout.presentation.components.CheckoutStep
import com.apparel.offprice.features.checkout.presentation.components.DeliveryTypeRow
import com.apparel.offprice.features.checkout.presentation.components.OrderSummarySection
import com.apparel.offprice.features.checkout.presentation.components.PickupStoreSection
import com.apparel.offprice.features.checkout.presentation.components.ShippingAddressFilter
import com.apparel.offprice.features.checkout.presentation.components.TopBar
import features.cart.presentation.component.PriceSummaryCard

@Composable
fun ShippingAddressScreen(
    viewModel: CheckOutViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onSaveAddress: () -> Unit
) {


    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            CheckOutContract.UiEffect.OnNavigateToBack -> {
                onNavigateBack()
            }
            CheckOutContract.UiEffect.NavigateToPayment -> {
                onSaveAddress() // navigate to payment screen
            }
        }
    }

    if (state.isAddressSheetOpen) {
        AddressSelectionBottomSheet(
            selected = state.selectedAddress,
            onDismiss = { event(CheckOutContract.UiEvent.OnCloseAddressSheet) },
            onAddressSelected = {
                event(CheckOutContract.UiEvent.OnAddressSelected(it))
            },
            onAddAddressClick = {
                event(CheckOutContract.UiEvent.OnOpenAddAddress)
            }
        )
    }

    if (state.isAddAddressSheetOpen) {
        AddAddressBottomSheet(
            onDismiss = {
                event(CheckOutContract.UiEvent.OnCloseAddAddress)
            },
            onSave = {
                event(CheckOutContract.UiEvent.OnCloseAddAddress)
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        // 🔹 FIXED TOP BAR
        TopBar(onBack = onNavigateBack)

        // 🔹 SCROLLABLE CONTENT
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            item {
                CheckoutProgressStepper(currentStep = 1)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // ALWAYS SHOW TOGGLE
            item {
                DeliveryTypeRow(
                    selectedFilter = state.selectedFilter,
                    onFilterSelected = {
                        event(CheckOutContract.UiEvent.OnFilterSelected(it))
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item {
                when (state.selectedFilter) {

                    // 🚚 DELIVERY FLOW
                    ShippingAddressFilter.DELIVERY -> {
                        when (state.checkoutStep) {

                            CheckoutStep.ADDRESS -> {
                                AddressForm()
                            }

                            CheckoutStep.SUMMARY -> {
                                Column {
                                    OrderSummarySection()

                                    Spacer(modifier = Modifier.height(20.dp))

                                    PriceSummaryCard(
                                        isOpenShipFee = state.isOpenShipFee,
                                        priceData = priceData.apply { isAutoCoupon = true },
                                        OnShipFeeClick = {
                                            event(CheckOutContract.UiEvent.OnShipFeeClick)
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // 🏬 PICKUP FLOW (NO SUMMARY EVER)
                    ShippingAddressFilter.PICKUPATSTORE -> {
                        PickupStoreSection()
                    }
                }
            }

        }



        // 🔹 FIXED BOTTOM BAR
        if (state.selectedFilter == ShippingAddressFilter.DELIVERY) {
            BottomBar(
                onSave = {
                    if (state.checkoutStep == CheckoutStep.ADDRESS) {
                        event(CheckOutContract.UiEvent.OnSaveAddressClicked)
                    } else {
                        //onProceedToPayment()
                    }
                },
                buttonText =
                    if (state.checkoutStep == CheckoutStep.ADDRESS){
                        stringResource(R.string.label_verify_save_address)
                    } else{
                        stringResource(R.string.proceed_to_payment)
                    },
                showArrow = state.checkoutStep == CheckoutStep.ADDRESS,
                grandTotal = priceData.grandTotal,
                onOpenAddressSheet = {
                    event(CheckOutContract.UiEvent.OnOpenAddressSheet)
                },
                state = state
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ShippingAddressScreenPreview() {
    ShippingAddressScreen(
        onNavigateBack = {},
        onSaveAddress = {}
    )
}
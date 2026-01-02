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
import com.apparel.offprice.features.checkout.presentation.components.PaymentScreenContent
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
            },
            event = event
        )
    }

    if (state.isAddAddressSheetOpen) {
        AddAddressBottomSheet(
            state = state,
            onDismiss = {
                event(CheckOutContract.UiEvent.OnCloseAddAddress)
            },
            onTypeSelected = {
                event(CheckOutContract.UiEvent.OnAddAddressTypeSelected(it))
            },
            onDefaultChecked = {
                event(CheckOutContract.UiEvent.OnDefaultAddressChecked(it))
            },
            onSave = {
                event(CheckOutContract.UiEvent.OnSaveNewAddress)
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        TopBar(
            title =
                if (state.checkoutStep == CheckoutStep.PAYMENT)
                    stringResource(R.string.label_payment)
                else
                    stringResource(R.string.checkout),
            onBack = onNavigateBack
        )

        // 🔹 SCROLLABLE CONTENT
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            item {
                CheckoutProgressStepper(
                    currentStep =
                        when (state.checkoutStep) {
                            CheckoutStep.ADDRESS,
                            CheckoutStep.SUMMARY -> 1

                            CheckoutStep.PAYMENT -> 2
                        }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // ALWAYS SHOW TOGGLE
            item {
                if (state.checkoutStep != CheckoutStep.PAYMENT) {
                    DeliveryTypeRow(
                        selectedFilter = state.selectedFilter,
                        onFilterSelected = {
                            event(CheckOutContract.UiEvent.OnFilterSelected(it))
                        }
                    )
                }
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
                                    Spacer(Modifier.height(20.dp))
                                    PriceSummaryCard(
                                        isOpenShipFee = state.isOpenShipFee,
                                        priceData = priceData.apply { isAutoCoupon = true },
                                        OnShipFeeClick = {
                                            event(CheckOutContract.UiEvent.OnShipFeeClick)
                                        }
                                    )
                                }
                            }

                            CheckoutStep.PAYMENT -> {
                                PaymentScreenContent(
                                    orderSummary = { OrderSummarySection() },
                                    priceSummary = {
                                        PriceSummaryCard(
                                            isOpenShipFee = false,
                                            priceData = priceData,
                                            OnShipFeeClick = {}
                                        )
                                    }
                                )
                            }
                        }
                    }

                    // 🏬 PICKUP FLOW (UNCHANGED)
                    ShippingAddressFilter.PICKUPATSTORE -> {
                        PickupStoreSection()
                    }
                }
            }


        }


        // 🔹 FIXED BOTTOM BAR
        if (
            state.selectedFilter == ShippingAddressFilter.DELIVERY &&
            state.checkoutStep != CheckoutStep.PAYMENT
        ) {
            BottomBar(
                onSave = {
                    if (state.checkoutStep == CheckoutStep.ADDRESS) {
                        event(CheckOutContract.UiEvent.OnSaveAddressClicked)
                    } else {
                        event(CheckOutContract.UiEvent.OnProceedToPaymentClicked)
                    }
                },
                buttonText =
                    if (state.checkoutStep == CheckoutStep.ADDRESS)
                        stringResource(R.string.label_verify_save_address)
                    else
                        stringResource(R.string.proceed_to_payment),
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
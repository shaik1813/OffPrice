package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.features.cart.data.priceData
import com.apparel.offprice.features.checkout.presentation.screens.CheckOutContract
import features.cart.presentation.component.PriceSummaryCard

@Composable
fun PickupStoreSection(
    state: CheckOutContract.UiState,
    event: (CheckOutContract.UiEvent) -> Unit
) {

    Column {

        if (!state.isPickupStoreSelected || state.selectedPickupStore == null) {
            // 🔹 SHOW FULL LIST
            state.pickupStores.forEach { store ->
                PickupStoreCard(
                    store = store,
                    selected = false,
                    onClick = {
                        event(CheckOutContract.UiEvent.OnPickupStoreSelected(store))
                    }
                )
                Spacer(Modifier.height(12.dp))
            }
        } else {
            // 🔹 SHOW ONLY SELECTED STORE
            PickupStoreCard(
                store = state.selectedPickupStore,
                selected = true,
                onClick = {
                    event(CheckOutContract.UiEvent.OnChangePickupStore)
                }
            )

            Spacer(Modifier.height(16.dp))

            // 🔹 CONTACT DETAILS
            PickupContactDetails(
                name = state.pickupName,
                phone = state.pickupPhone,
                onNameChange = {
                    event(CheckOutContract.UiEvent.OnPickupNameChanged(it))
                },
                onPhoneChange = {
                    event(CheckOutContract.UiEvent.OnPickupPhoneChanged(it))
                }
            )

            Spacer(Modifier.height(16.dp))

            // 🔹 ORDER SUMMARY
            OrderSummarySection()

            Spacer(Modifier.height(16.dp))

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


package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.features.checkout.presentation.screens.CheckOutContract

@Composable
fun PaymentScreenContent(
    state: CheckOutContract.UiState,
    event: (CheckOutContract.UiEvent) -> Unit,
    orderSummary: @Composable () -> Unit,
    priceSummary: @Composable () -> Unit
) {
    Column {
        PaymentMethodsSection(
            state = state,
            event = event
        )

        Spacer(Modifier.height(16.dp))

        orderSummary()

        Spacer(Modifier.height(16.dp))

        priceSummary()

    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenContentPreview() {
    PaymentScreenContent(
        state = CheckOutContract.UiState(),
        event = {},
        orderSummary = { },
        priceSummary = { }
    )
}


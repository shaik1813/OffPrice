package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun PaymentScreenContent(
    orderSummary: @Composable () -> Unit,
    priceSummary: @Composable () -> Unit
) {
    Column {
        PaymentMethodsSection()

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.label_order_summary),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        orderSummary()

        Spacer(Modifier.height(16.dp))

        priceSummary()

    }
}


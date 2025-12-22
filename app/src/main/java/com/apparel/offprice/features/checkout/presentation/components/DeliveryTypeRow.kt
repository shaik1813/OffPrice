package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.features.storeCredit.presentation.component.FilterChip

@Composable
fun DeliveryTypeRow(
    selectedFilter: ShippingAddressFilter,
    onFilterSelected: (ShippingAddressFilter) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, buttonBorderColor.copy(alpha = 0.4f)),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.delivery),
                selected = selectedFilter == ShippingAddressFilter.DELIVERY,
                onClick = { onFilterSelected(ShippingAddressFilter.DELIVERY) },
            )
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.pick_up_at_store),
                selected = selectedFilter == ShippingAddressFilter.PICKUPATSTORE,
                onClick = { onFilterSelected(ShippingAddressFilter.PICKUPATSTORE) },
            )
        }
    }
}


enum class ShippingAddressFilter {
    DELIVERY, PICKUPATSTORE
}

@Preview(showBackground = true)
@Composable
fun DeliveryTypeRowPreview() {
    DeliveryTypeRow(selectedFilter = ShippingAddressFilter.DELIVERY, onFilterSelected = {})
}

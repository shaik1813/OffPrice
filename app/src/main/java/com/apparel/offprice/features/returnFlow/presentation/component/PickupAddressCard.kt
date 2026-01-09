package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.features.checkout.presentation.components.AddressRow

@Composable
fun PickupAddressCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "PICK UP ADDRESS",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                AssistChip(
                    onClick = {},
                    label = { Text("Home") }
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Michael Jonathon",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(Modifier.height(8.dp))

            AddressRow(
                icon = Icons.Default.LocationOn,
                text = "Flat 402, Al Zahra Building Al Nahda Street, Nahda 2, Dubai, UAE"
            )

            Spacer(Modifier.height(8.dp))

            AddressRow(
                icon = Icons.Default.Call,
                text = "+971 436842594"
            )
        }
    }
}

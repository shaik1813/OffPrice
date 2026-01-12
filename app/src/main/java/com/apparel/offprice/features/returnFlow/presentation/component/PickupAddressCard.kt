package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.surfaceColor

@Composable
fun PickupAddressCard() {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, surfaceColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = stringResource(R.string.pick_up_address_label),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(color = surfaceColor)

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = surfaceColor
                ) {
                    Text(
                        text = stringResource(R.string.label_delivery_address_home),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Spacer(Modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.temp_name),
                    style = MaterialTheme.typography.titleSmall
                )
            }


            Spacer(Modifier.height(8.dp))

            AddressRow(
                iconRes = R.drawable.icon_location_black,  // your location drawable
                text = stringResource(R.string.temp_address)
            )

            Spacer(Modifier.height(8.dp))

            AddressRow(
                iconRes = R.drawable.icon_phone,  // your phone drawable
                text = stringResource(R.string.temp_number)
            )

        }
    }
}

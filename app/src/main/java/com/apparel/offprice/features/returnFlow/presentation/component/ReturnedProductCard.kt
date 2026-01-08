package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun ReturnedProductCard() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row {
                Image(
                    painter = painterResource(R.drawable.colorimg), // replace
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = "ADIDAS",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        text = "Printed Shirt With Crew Neck And Short Sleeves",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )

                    Spacer(Modifier.height(4.dp))

                    Text("Color: Blue    Qty: 01    Size: L")

                    Text(
                        text = "₹35.00",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "RRP ₹172.00 (90% OFF)",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "Reason: Received Wrong Item",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null
                )
            }

            Spacer(Modifier.height(12.dp))

            StatusDotText(
                text = "Approved",
                color = Color(0xFF4CAF50)
            )
        }
    }
}

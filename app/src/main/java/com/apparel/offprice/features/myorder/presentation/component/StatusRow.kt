package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.primaryColor

@Composable
fun StatusRow(status: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter =
                    if (status == "Order Received")
                        painterResource(R.drawable.order_received_icon)
                    else if (status == "Out For Delivery")
                        painterResource(R.drawable.outdelivery_icon)
                    else
                        painterResource(R.drawable.order_shipped_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                status, fontSize = 14.sp,
                color = primaryColor,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
            )
        }


        Image(
            painter = painterResource(R.drawable.right_arrow),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}

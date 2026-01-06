package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.features.pdp.data.model.TabbyPaymentItem


@Composable
fun TabbyPaymentInfoCard(tabbyData: TabbyPaymentItem) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = tabbyData.paymentCount.toString() + " payments",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 12.sp,
                color = saleCardColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (tabbyData.paymentCount == 0) "No interest. No fees." else "Includes",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 10.sp,
                    color = if (tabbyData.paymentCount == 0) Color(0xFF4CAF50) else saleCardColor
                )

                Image(
                    colorFilter = ColorFilter.tint(Color(0xFF8C8D8D)),
                    painter = painterResource(R.drawable.icon_currency_uae),
                    contentDescription = null,
                    modifier = Modifier
                        .width(8.dp)
                        .height(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "in total fees",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 10.sp,
                    color = if (tabbyData.paymentCount == 0) Color(0xFF4CAF50) else saleCardColor
                )
            }
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                colorFilter = ColorFilter.tint(saleCardColor),
                painter = painterResource(R.drawable.icon_currency_uae),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .width(10.dp)
                    .height(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = " ${tabbyData.amount}",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 12.sp,
                color = saleCardColor
            )
        }
    }
}
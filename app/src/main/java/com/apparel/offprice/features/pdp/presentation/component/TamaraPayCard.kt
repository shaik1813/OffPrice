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
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.features.pdp.data.model.TamaraPaymentItem


@Composable
fun TamaraPayCard(tamaraData: TamaraPaymentItem) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    colorFilter = ColorFilter.tint(saleCardColor),
                    painter = painterResource(R.drawable.icon_currency_uae),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .width(10.dp)
                        .height(10.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = tamaraData.amount.toString() + "/mo",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 14.sp,
                    color = saleCardColor
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "No fees",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                color = Color(0xFF4CAF50)
            )
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .background(borderColor, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (tamaraData.paymentCount == 0) "Pay in Full" else "${tamaraData.paymentCount} Payments",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 12.sp,
                color = saleCardColor
            )
        }
    }
}

package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.myorder.data.OrderItem

@Composable
fun OrderCard(screenWidth: Dp, order: OrderItem) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color(0xFFE6E6E6)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
             ) {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(surfaceColor)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = stringResource(com.apparel.offprice.R.string.order) + order.orderId,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = primaryColor
                    )
                }

                VerticalDivider(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(15.dp),
                    color = Color(0xFFB0B0B0)
                )

                Text(
                    text = "${order.items.size} items",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryColor
                )
            }

            Spacer(modifier = Modifier.size(24.dp))

            if (order.items.size > 1) {
                MultiItemRow(screenWidth,order)
            } else {
                SingleItemRow(screenWidth,order.items.first())
            }

            Spacer(modifier = Modifier.height(12.dp))

            StatusRow(order.orderStatus)
        }
    }
}

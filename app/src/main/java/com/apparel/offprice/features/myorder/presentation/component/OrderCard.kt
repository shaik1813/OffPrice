package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.features.myorder.data.OrderItem

@Composable
fun OrderCard(order: OrderItem) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color(0xFFB0B0B0)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(com.apparel.offprice.R.string.order)+order.orderId,
                    fontSize = 12.sp,
                    color = primaryColor
                )
                Text(
                    text = "${order.items.size} items",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            VerticalDivider(modifier = Modifier.padding(horizontal = 12.dp).height(15.dp),
                color = Color(0xFFB0B0B0))

            if (order.items.size > 1) {
                MultiItemRow(order)
            } else {
                SingleItemRow(order.items.first())
            }

            Spacer(modifier = Modifier.height(12.dp))

            StatusRow(order.orderStatus)
        }
    }
}

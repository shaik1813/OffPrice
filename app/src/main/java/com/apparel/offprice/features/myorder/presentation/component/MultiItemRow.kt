package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.features.myorder.data.OrderItem


@Composable
fun MultiItemRow(order: OrderItem) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        order.items.take(3).forEach {
            Image(
                painter = painterResource(it.image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        if (order.items.size > 3) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(borderColor),
                contentAlignment = Alignment.Center
            ) {
                Text("+${order.items.size - 3}\nItems", fontSize = 12.sp)
            }
        }
    }
}

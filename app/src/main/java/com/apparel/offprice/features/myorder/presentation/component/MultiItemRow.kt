package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.features.myorder.data.OrderItem


@Composable
fun MultiItemRow(screenWidth: Dp, order: OrderItem) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(order.items) { index, item ->
            if(index<3) {
                Image(
                    painter = painterResource(item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(screenWidth / 5.42f)
                        .height((screenWidth / 5.42f) * 1.71f)
                        .clip(RoundedCornerShape(
                            8.dp))
                        .background(brush = productCardColor)
                )
            }

            if (index > 2) {
                Box(
                    modifier = Modifier
                        .width(screenWidth / 5.42f)
                        .height((screenWidth / 5.42f) * 1.71f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = productCardColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+2\nItems",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp)
                }
            }
        }


    }
}

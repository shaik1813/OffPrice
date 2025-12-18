package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
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


@Composable
fun PriceCardUI(
    title: String,
    value: String,
    valueColor: Color = Color.Black,
    isBold: Boolean = false,
    showArrow: Boolean = false,
    shipFeeClick : () -> Unit ={}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(indication = null, interactionSource = null){
                shipFeeClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                fontSize = 13.sp,
                style = if (isBold) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
                color = Color(0xFF040707)
            )

            if (showArrow) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
            }
        }

        Row() {
            Image(
                colorFilter = ColorFilter.tint(valueColor),
                painter = painterResource(R.drawable.icon_currency_uae),
                contentDescription = null,
                modifier = Modifier
                    .width(12.dp)
                    .height(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = value,
                fontSize = 13.sp,
                style = if (isBold) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
                color = valueColor,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}



@Composable
fun ShippingFeeUI(
    title: String,
    value: String,
    valueColor: Color = Color.Black,
    showArrow: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                fontSize = 13.sp,
                style =  MaterialTheme.typography.bodyMedium,
                color = valueColor
            )

            if (showArrow) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
            }
        }

        Row() {
            Image(
                colorFilter = ColorFilter.tint(valueColor),
                painter = painterResource(R.drawable.icon_currency_uae),
                contentDescription = null,
                modifier = Modifier
                    .width(12.dp)
                    .height(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = value,
                fontSize = 13.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = valueColor,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

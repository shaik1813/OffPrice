package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R


@Composable
fun QuantityRow(
    value: Int,
    selectedQty: Int,
    onClick: (value: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(value) }
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value.toString(), style = MaterialTheme.typography.titleMedium,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 16.dp)
        )


        if (selectedQty == value) {
            Icon(
                painter = painterResource(R.drawable.tick_icon),
                contentDescription = "Selected",
                tint = Color.Black
            )
        }
    }

    HorizontalDivider(
        color = Color(0xFFB1B2B2),
        thickness = 0.5.dp
    )
}

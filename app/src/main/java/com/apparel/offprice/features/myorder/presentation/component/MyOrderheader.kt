package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.R

@Composable
fun MyOrderheader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.apparel.offprice.R.string.my_order)+"(6)",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
            color = saleCardColor
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(com.apparel.offprice.R.string.all_orders),
                style = MaterialTheme.typography.titleMedium,
                color = saleCardColor,
                fontSize = 12.sp)
            Image(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0x66292D32)),
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(16.dp)
            )
        }
    }
}

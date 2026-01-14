package com.apparel.offprice.features.myorder.presentation.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.R
import com.apparel.offprice.common.component.noRippleClickable
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.features.myorder.data.OrderFilter
import com.apparel.offprice.features.myorder.data.OrderItem

@Composable
fun MyOrderheader(
    selectFilter:OrderFilter,
    onAllOrdersClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.apparel.offprice.R.string.my_order) + "(6)",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
            color = saleCardColor
        )

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .noRippleClickable {
                    onAllOrdersClicked()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                getString(selectFilter, LocalContext.current),
                style = MaterialTheme.typography.titleMedium,
                color = saleCardColor,
                fontSize = 12.sp
            )
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


private fun getString(filter: OrderFilter, ctx: Context): String {
    return when (filter) {
        OrderFilter.ALL -> ctx.getString(com.apparel.offprice.R.string.all_orders)
        OrderFilter.ORDER_RECEIVED -> ctx.getString(com.apparel.offprice.R.string.delivered)
        OrderFilter.CANCELLED -> ctx.getString(com.apparel.offprice.R.string.cancelled)
        OrderFilter.INPROGRESS -> ctx.getString(com.apparel.offprice.R.string.in_progress)
        OrderFilter.DELIVERED -> ctx.getString(com.apparel.offprice.R.string.delivered)
    }
}
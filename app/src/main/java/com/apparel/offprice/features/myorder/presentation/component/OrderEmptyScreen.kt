package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.saleCardColor

@Composable
fun OrderEmptyScreen(
    onViewAllOrdersClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.empty_order_icon),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
            )

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = stringResource(R.string.label_no_orders),
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
                color = primaryColor
            )


            Text(
                text = stringResource(R.string.text_no_orders_description),
                fontSize = 14.sp,
                color = nonreturnTxtColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
            )

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .height(46.dp)
                    .wrapContentWidth()
                    .background(saleCardColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 30.dp)
                    .clickable { onViewAllOrdersClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.label_view_all_orders),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
                    fontSize = 14.sp,
                )
            }
        }
    }
}
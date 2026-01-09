package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.primaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBottomView(onCheckOutClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        grandTotalsection()

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(46.dp)
                .fillMaxWidth()
                .background(loginButtonColor, RoundedCornerShape(8.dp))
                .clickable { onCheckOutClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.checkout),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(R.drawable.right_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(17.dp)
                        .width(17.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }


}


@Composable
fun grandTotalsection(){
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(R.string.grand_total),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight(700),
                fontSize = 14.sp,
                color = primaryColor
            )
        )

        Row{
            Image(
                colorFilter = ColorFilter.tint(primaryColor),
                painter = painterResource(R.drawable.icon_currency_uae),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 2.dp)
                    .width(12.dp)
                    .height(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "97.00",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 14.sp,
                    color = primaryColor
                )
            )
        }
    }

}
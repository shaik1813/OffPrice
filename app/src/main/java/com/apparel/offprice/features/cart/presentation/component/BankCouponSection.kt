package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.features.cart.data.BankCouponItems


@Composable
fun BankCouponSection(item: BankCouponItems) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Text(
            text = stringResource(R.string.bank_coupon),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(14.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(modifier = Modifier.weight(0.23f)) {
                Image(
                    painter = painterResource(R.drawable.bank_couponbg),
                    contentDescription = null,
                    modifier = Modifier
                        .width(93.dp)
                        .height(79.dp)
                        .align(Alignment.Center)
                )

                Row(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        "25",
                        color = Color.White,
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            "%",
                            color = Color.White,
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            "Offer",
                            color = Color.White,
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.titleMedium,

                            )
                    }
                }
            }



            Box(
                modifier = Modifier
                    .weight(0.77f)
                    .height(79.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bank_couponbg2),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(79.dp)
                )

                Column(modifier = Modifier.align(Alignment.CenterStart)
                    .padding(start = 6.dp)) {
                    Text(
                        item.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        color = loginButtonColor
                    )


                    Spacer(modifier = Modifier.size(2.dp))

                    Text(
                        item.desc,
                        fontSize = 12.sp,
                        color = lineColor,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    VerticalDivider(
                        modifier = Modifier
                            .padding(10.dp)
                            .width(1.dp)
                            .fillMaxHeight()
                            .background(Color(0xFFB0B0B0))
                    )

                    Image(
                        painter = painterResource(R.drawable.coupon_fabicon),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 23.dp).width(32.dp).height(24.dp).
                        align(Alignment.CenterVertically)
                    )

                }
            }


        }


    }
}

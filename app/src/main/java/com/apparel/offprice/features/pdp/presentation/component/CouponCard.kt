package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.R


@Composable
fun CouponCard(OfferClick: () -> Unit) {

    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(com.apparel.offprice.R.string.apply_coupon),
                color = loginButtonColor,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.size(10.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(1.dp),
                shape = RoundedCornerShape(6.dp),

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "WELCO",
                        color = loginButtonColor,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "Apply",
                        color = Color(0xFF4CAF50),
                        fontSize = 12.sp
                    )
                }

            }


            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
                Row(modifier = Modifier.align(Alignment.Center).clickable{
                    OfferClick()
                }) {
                    Image(
                        painter = painterResource(R.drawable.coupon_icon),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.view_all_offers),
                        color = loginButtonColor,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }

        }
    }
}
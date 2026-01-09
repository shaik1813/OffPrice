package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.features.cart.presentation.screen.CartContract


@Composable
fun CouponCard(
    isApplied: Boolean,
    couponCode:String,
    OnCouponChange : (String) -> Unit,
    OnApply : (String) -> Unit,
    OfferClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(com.apparel.offprice.R.string.apply_coupon),
                color = saleCardColor,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700),
                    fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.size(10.dp))

            Surface(
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, borderColor),
                color = Color.White
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(0.75f)
                            .align(Alignment.CenterVertically)
                    ) {

                        if (isApplied) {
                            Image(
                                painter = painterResource(R.drawable.cart_tick_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(20.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        BasicTextField(
                            value = couponCode,
                            onValueChange = { it ->
                                OnCouponChange(it)
                            },
                            textStyle = MaterialTheme.typography.titleMedium.copy(
                                color = loginButtonColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600)
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Text(
                        text = if (isApplied) stringResource(R.string.remove) else stringResource(R.string.apply),
                        color = if (isApplied) Color(0xFFB5373D) else Color(0xFF4CAF50),
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                        modifier = Modifier
                            .weight(0.25f)
                            .clickable(indication = null, interactionSource = null) {
                                OnApply(couponCode)
                            },
                    )
                }

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable(indication = null, interactionSource = null) {
                            OfferClick()
                        }) {
                    Image(
                        painter = painterResource(R.drawable.coupon_icon),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = stringResource(R.string.view_all_offers),
                        color = loginButtonColor,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }

        }
    }
}
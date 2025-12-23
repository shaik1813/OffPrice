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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.features.cart.presentation.screen.CartContract


@Composable
fun CouponCard(
    state: CartContract.UiState,
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
                color = loginButtonColor,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.size(10.dp))

            Surface(
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
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

                        if (state.isApplied) {
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
                            value = state.couponCode,
                            onValueChange = { it ->
                                OnCouponChange(it)
                            },
                            textStyle = MaterialTheme.typography.titleMedium.copy(
                                color = loginButtonColor,
                                fontSize = 12.sp
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Text(
                        text = if (state.isApplied) stringResource(R.string.remove) else stringResource(R.string.apply),
                        color = if (state.isApplied) Color(0xFFB5373D) else Color(0xFF4CAF50),
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .weight(0.25f)
                            .clickable(indication = null, interactionSource = null) {
                                OnApply(state.couponCode)
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
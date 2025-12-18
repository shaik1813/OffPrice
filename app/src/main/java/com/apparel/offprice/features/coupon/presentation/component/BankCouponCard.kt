package com.apparel.offprice.features.coupon.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.coupon.data.BankCouponModel

@Composable
fun BankCouponCard(
    bankCoupon: BankCouponModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 6.dp,
        ),
       shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box {
                Image(
                    painter = painterResource(R.drawable.bank_couponbg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(93.dp)
                        .height(70.dp)
                )
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${bankCoupon.discountPercentage}",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontSize = 28.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight(800)
                        ),
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = "%",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = (-0.11).sp,
                            ),
                            color = Color.White,
                        )
                        Text(
                            text = "offer",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.12).sp,
                            ),
                            color = Color.White,
                        )
                    }
                }
            }

            // Middle - Description
            Box(
             contentAlignment = Alignment.Center,
            ){
                Image(
                    painter = painterResource(id = R.drawable.bank_coupon_bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(70.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = bankCoupon.description,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.12).sp,
                            ),
                            color = Color(0xFF040707),
                        )
                        Text(
                            text = bankCoupon.minPurchase,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                letterSpacing = (-0.12).sp,
                            ),
                            color = Color(0xFF545454),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(0.4f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        VerticalDivider(
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxHeight(0.8f)
                            ,
                            color = Color(0xFFB0B0B0)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            painter = painterResource(R.drawable.coupon_fabicon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(44.dp)
                                .height(25.dp)
                        )
                    }
                }
            }
        }
    }
}
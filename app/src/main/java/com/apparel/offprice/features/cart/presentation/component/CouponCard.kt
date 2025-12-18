package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@Composable
fun CouponCard(
    title: String,
    code: String,
    description: String,
    onItemClick: (String) -> Unit,
    onClickOfferDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                // Top gradient section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFF05F57),
                                    Color(0xFF360940)
                                )
                            )
                        )

                        .padding(vertical = 10.dp, horizontal = 16.dp)
                ) {

                    Row() {
                        Image(
                            painter = painterResource(R.drawable.offer_couponicon),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(40.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Column(modifier = Modifier.align(Alignment.CenterVertically)) {

                            Text(
                                title, color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 12.sp
                            )
                            Text(
                                text = code,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomStart)
                        .offset(x = (-20).dp, y = 20.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )

                // Right half circle cut
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (20).dp, y = 20.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }


            // Bottom content
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                DottedLine()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "T&C*",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleMedium,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{
                            onClickOfferDetail()
                        }
                    )

                    Text(
                        text = stringResource(R.string.apply_code),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 13.sp,
                        modifier = Modifier.clickable { onItemClick(code) }
                    )
                }
            }
        }
    }
}

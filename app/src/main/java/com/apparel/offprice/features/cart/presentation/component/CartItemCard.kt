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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor


@Composable
fun CartItemCard(
    brand: String,
    title: String,
    price: String,
    oldPrice: String?,
    color: String,
    size: String,
    qty: String,
    deliveryText: String,
    image: Painter,
    onDelete: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Row {

                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(140.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8E8E8)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = brand,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Offer ends in 11:56:38",
                            color = Color(0xFFA2050D),
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = title,
                        fontSize = 10.sp,
                        color = Color(0xFF545454),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(4.dp))

                    ColorSizePart(color, qty)

                    Spacer(Modifier.height(2.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = price,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = loginButtonColor
                        )
                        Spacer(Modifier.width(6.dp))
                        if (oldPrice != null) {
                            Text(
                                text = oldPrice,
                                fontSize = 10.sp,
                                color = Color(0xFF666666),
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    OfferPricePart()

                    Spacer(modifier = Modifier.height(4.dp))

                    // DELIVERY BADGE
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFFDB300), RoundedCornerShape(20.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {

                            Image(
                                painter = painterResource(R.drawable.arrive_icon),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.size(2.dp))

                            Text(
                                text = deliveryText,
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                        }

                    }

                    Box(modifier = Modifier.fillMaxWidth()) {

                        Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                            Image(
                                painter = painterResource(R.drawable.heart_border),
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { onDelete }
                            )


                            Spacer(modifier = Modifier.size(10.dp))

                            Image(
                                painter = painterResource(R.drawable.delete_carticon),
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { onDelete() }
                            )


                        }
                    }


                }

                Spacer(modifier = Modifier.width(8.dp))


            }
        }
    }

}


@Composable
fun OfferPricePart() {
    Row() {
        Text(text = "RRP ฿ 172.00", color = Color(0xFF575959), fontSize = 10.sp)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = "(90% OFF)", fontSize = 10.sp, color = Color(0xFFED1D2C))

    }
}

@Composable
fun ColorSizePart(color: String, size: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(R.string.color), fontSize = 10.sp)
        Text(
            text = color, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 10.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "|", fontSize = 10.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(text = stringResource(R.string.size), fontSize = 10.sp)
        Text(
            text = size, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 10.sp
        )


        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "|", fontSize = 10.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(text = "Qty:", fontSize = 10.sp)

        QuantityPart()
    }
}

@Composable
fun QuantityPart() {

    Column(modifier = Modifier.padding(start = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "01", color = loginButtonColor, fontSize = 10.sp)
            Spacer(modifier = Modifier.size(3.dp))
            Image(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = null, modifier = Modifier.size(10.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .width(29.dp)
                .height(1.dp)
                .background(Color(0xFF040707))
        )
    }
}


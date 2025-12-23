package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.features.cart.data.CartProductItems


@Composable
fun CartItemCard(
    itemProduct: CartProductItems,
    selectQuantity: (pos: Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row {

                Image(
                    painter = painterResource(itemProduct.image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(140.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8E8E8))
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
                            text = itemProduct.brand,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row(
                            modifier = Modifier
                                .background( Color(0x80F6E6E7), RoundedCornerShape(12.dp))
                                .padding(horizontal = 2.dp, vertical = 1.2.dp),
                             verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(R.drawable.offer_timeicon),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.size(2.dp))

                            Text(
                                text = "Offer ends in 11:56:38",
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFA2050D),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = itemProduct.title,
                        fontSize = 10.sp,
                        color = Color(0xFF545454),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(4.dp))

                    ColorSizePart(
                        itemProduct.color,
                        itemProduct.size,
                        itemProduct.quantity.toString(),
                        callBackQuantity = {
                            selectQuantity(itemProduct.id)
                        })

                    Spacer(Modifier.height(2.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = String.format("%.2f", itemProduct.basePrice) ,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = loginButtonColor
                        )
                        Spacer(Modifier.width(6.dp))
                        if (itemProduct.discountPrice != null) {
                            Text(
                                text = String.format("%.2f", itemProduct.discountPrice) ,
                                fontSize = 10.sp,
                                color = Color(0xFF666666),
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    OfferPricePart(String.format("%.2f", itemProduct.rrp), itemProduct.discount)

                    Spacer(modifier = Modifier.height(4.dp))

                    // DELIVERY BADGE
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFEFEFEF), RoundedCornerShape(20.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {

                            Image(
                                painter = painterResource(R.drawable.arrive_icon),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.size(2.dp))

                            Text(
                                text = itemProduct.delivery,
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
                                    .clickable { }
                            )


                            Spacer(modifier = Modifier.size(10.dp))

                            Image(
                                painter = painterResource(R.drawable.delete_carticon),
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { onDelete(itemProduct.id) }
                            )


                        }
                    }


                }

                Spacer(modifier = Modifier.width(8.dp))


            }
        }
        ///




    }

}


@Composable
fun OfferPricePart(RrpPrice: String, discount: String) {
    Row {
        Text(text = "RRP ฿ " + RrpPrice, color = Color(0xFF575959), fontSize = 10.sp)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = "("+discount+" OFF)", fontSize = 10.sp, color = Color(0xFFED1D2C))
    }
}

@Composable
fun ColorSizePart(color: String, size: String, quantity: String, callBackQuantity: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(R.string.color), fontSize = 10.sp)
        Text(
            text = " "+color, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 10.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "|", fontSize = 10.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(text = stringResource(R.string.size), fontSize = 10.sp)
        Text(
            text = " "+size, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 10.sp
        )


        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "|", fontSize = 10.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(text = "Qty:", fontSize = 10.sp)

        QuantityPart(quantity, callBackQuantity = { callBackQuantity() })
    }
}

@Composable
fun QuantityPart(qty: String, callBackQuantity: () -> Unit) {

    Column(modifier = Modifier
        .padding(start = 4.dp)
        .clickable {
            callBackQuantity()
        }) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = qty, color = loginButtonColor, fontSize = 10.sp)
            Spacer(modifier = Modifier.size(3.dp))
            Image(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = null, modifier = Modifier.padding(start = 4.dp).size(8.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .width(25.dp)
                .height(1.dp)
                .background(Color(0xFF040707))
                .align(Alignment.CenterHorizontally)
        )
    }
}


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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.errorColor
import com.apparel.offprice.common.theme.greenColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.offerCardColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.common.theme.wixMadeForTextFamily
import com.apparel.offprice.features.cart.data.CartProductItems


@Composable
fun CartItemCard(
    itemProduct: CartProductItems,
    selectQuantity: (pos: Int) -> Unit,
    onWishListClick: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    screenWidth: Dp
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, borderColor),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Box(modifier = Modifier.fillMaxWidth()) {

                Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                    Image(
                        painter = if(itemProduct.isWishlist)
                            painterResource(R.drawable.cart_heartfilled)
                        else painterResource(R.drawable.cart_heart_notfilled),
                        contentDescription = "Wishlist",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onWishListClick(itemProduct.id) }
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

            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(itemProduct.image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(screenWidth / 4.3f)
                        .height((screenWidth / 4.3f) * 1.55f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = productCardColor)
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
                            color = primaryColor,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight(
                                    600
                                )
                            )
                        )

                        Row(
                            modifier = Modifier
                                .background(Color(0x80F6E6E7), RoundedCornerShape(12.dp))
                                .padding(horizontal = 2.dp, vertical = 1.2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(R.drawable.offer_timeicon),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.size(2.dp))

                            Text(
                                text = "Offer ends in ",
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFA2050D),
                            )
                            Text(
                                text = "11:56:38",
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(800)),
                                color = Color(0xFFA2050D),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(7.dp))

                    Text(
                        text = itemProduct.title,
                        fontSize = 10.sp,
                        color = inputTextColor,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
                    )

                    Spacer(Modifier.height(8.dp))

                    ColorSizePart(
                        itemProduct.color,
                        itemProduct.size,
                        itemProduct.quantity.toString(),
                        callBackQuantity = {
                            selectQuantity(itemProduct.id)
                        })

                    Spacer(Modifier.height(2.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            colorFilter = ColorFilter.tint(errorColor),
                            painter = painterResource(R.drawable.icon_currency_uae),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 2.dp)
                                .width(12.dp)
                                .height(12.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = String.format("%.2f", itemProduct.basePrice),
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = errorColor
                        )

                        Spacer(Modifier.width(3.dp))

                        if (itemProduct.discountPrice != null) {
                            Image(
                                colorFilter = ColorFilter.tint(nonreturnTxtColor),
                                painter = painterResource(R.drawable.icon_currency_uae),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .width(10.dp)
                                    .height(10.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(
                                text = String.format("%.2f", itemProduct.discountPrice),
                                fontSize = 10.sp,
                                color = lineColor,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                        CouponStatusText(itemProduct.isCouponApply)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    OfferPricePart(String.format("%.2f", itemProduct.rrp), itemProduct.discount)

                    Spacer(modifier = Modifier.height(10.dp))

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
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontFamily = wixMadeForTextFamily, fontStyle = FontStyle.Italic,
                                    color = Color(0xFF000000), fontWeight = FontWeight(500)
                                )
                            )
                        }

                    }


                }

                Spacer(modifier = Modifier.width(8.dp))


            }

            }
        }
    }

}


@Composable
fun CouponStatusText(status:Int){
    Surface(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 3.dp),
        shape = RoundedCornerShape(4.dp),
        color = if(status == 0) offerCardColor else greenColor,
    ) {

        Row(modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(R.drawable.discount_whiteicon),
                contentDescription = null,
                modifier = Modifier.size(12.dp))

            Text(text = if(status == 2) stringResource(R.string.auto_coupon_applied)
                else if(status == 1) stringResource(R.string.coupon_applied)
            else stringResource(R.string.coupon_not_applicable),
                fontSize = 9.sp,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500),
                    lineHeight = 10.sp),
                color = Color.White,
                modifier = Modifier.padding(start = 3.dp))
        }
    }
}

@Composable
fun OfferPricePart(RrpPrice: String, discount: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "RRP", color = Color(0xFF575959),
            fontSize = 10.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )

        Image(
            colorFilter = ColorFilter.tint(nonreturnTxtColor),
            painter = painterResource(R.drawable.icon_currency_uae),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 2.dp)
                .width(10.dp)
                .height(10.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = RrpPrice, color = nonreturnTxtColor,
            fontSize = 10.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )

        Spacer(modifier = Modifier.size(5.dp))

        Text(text = "(" + discount + " OFF)", fontSize = 10.sp, color = errorColor)
    }
}

@Composable
fun ColorSizePart(color: String, size: String, quantity: String, callBackQuantity: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.color), fontSize = 10.sp,
            color = inputTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )
        Text(
            text = " " + color, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 10.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "|", fontSize = 10.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(R.string.size), fontSize = 10.sp,
            color = inputTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )

        Text(
            text = " " + size, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 10.sp
        )


        Spacer(modifier = Modifier.size(10.dp))

        Text(text = "|", fontSize = 10.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = "Qty:", fontSize = 10.sp, color = inputTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )

        QuantityPart(quantity, callBackQuantity = { callBackQuantity() })
    }
}

@Composable
fun QuantityPart(qty: String, callBackQuantity: () -> Unit) {

    Column(
        modifier = Modifier
            .padding(start = 4.dp)
            .clickable {
                callBackQuantity()
            }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = qty, color = loginButtonColor, fontSize = 10.sp)
            Spacer(modifier = Modifier.size(3.dp))
            Image(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = null, modifier = Modifier
                    .padding(start = 4.dp)
                    .size(8.dp)
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


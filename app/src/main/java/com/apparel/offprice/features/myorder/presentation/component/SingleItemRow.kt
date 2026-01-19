package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.apparel.offprice.common.theme.errorColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.common.theme.wixMadeForTextFamily
import com.apparel.offprice.features.cart.data.CartProductItems
import com.apparel.offprice.features.cart.presentation.component.ColorSizePart
import com.apparel.offprice.features.cart.presentation.component.CouponStatusText
import com.apparel.offprice.features.cart.presentation.component.OfferPricePart
import com.apparel.offprice.features.cart.presentation.component.QuantityPart

@Composable
fun SingleItemRow(screenWidth: Dp, item: CartProductItems) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource(item.image),
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
                    text = item.brand,
                    fontSize = 12.sp,
                    color = primaryColor,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight(
                            600
                        )
                    )
                )

            }

            Spacer(modifier = Modifier.size(7.dp))

            Text(
                text = item.title,
                fontSize = 10.sp,
                maxLines = 1,
                color = inputTextColor,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
            )

            Spacer(Modifier.height(8.dp))

            ColorSizePartOrder(
                item.color,
                item.size)

            Spacer(Modifier.height(8.dp))

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
                    text = String.format("%.2f", item.basePrice),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleMedium,
                    color = errorColor
                )

                Spacer(Modifier.width(3.dp))

                if (item.discountPrice != null) {
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
                        text = String.format("%.2f", item.discountPrice),
                        fontSize = 10.sp,
                        color = lineColor,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            OfferPricePart(String.format("%.2f", item.rrp), item.discount)

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
                        text = item.delivery,
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



@Composable
fun ColorSizePartOrder(color: String, size: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.color), fontSize = 12.sp,
            color = inputTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )
        Text(
            text = " " + color, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 12.sp
        )

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "|", fontSize = 12.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(R.string.size), fontSize = 12.sp,
            color = inputTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )

        Text(
            text = " " + size, style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor, fontSize = 12.sp
        )


        Spacer(modifier = Modifier.size(10.dp))

        Text(text = "|", fontSize = 12.sp, color = Color(0xFF8A8A8A))
        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = "Qty:", fontSize = 12.sp, color = inputTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
        )

        Text(text = "01", color = loginButtonColor,
            style = MaterialTheme.typography.titleMedium, fontSize = 12.sp)


    }
}

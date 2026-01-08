package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.errorColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.features.checkout.data.ProductItem
import com.apparel.offprice.features.checkout.data.sampleProductsOSS

@Composable
fun OrderSummarySection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = stringResource(R.string.label_order_summary),
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        sampleProductsOSS.take(3).forEachIndexed { index, product ->

            OrderSummaryItem(product = product)

            if (index != sampleProductsOSS.take(3).lastIndex) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = borderColor,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}


@Composable
fun OrderSummaryItem(product: ProductItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {

        // PRODUCT IMAGE
        Image(
            painter = painterResource(product.image),
            contentDescription = null,
            modifier = Modifier
                .width(90.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(productCardColor)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // BRAND
            Text(
                text = product.brand,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            // TITLE
            Text(
                text = product.title,
                maxLines = 2,
                color = inputTextColor,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            // COLOR | QTY | SIZE
            Text(
                text = "Color: ${product.color}   |   Qty: ${product.qty}   |   Size: ${product.size}",
                color = inputTextColor,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "฿ ${product.price}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.width(8.dp))

            // PRICE ROW — SAME LINE (KEY FIX)
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "RRP ฿ ${product.mrp}",
                    style = MaterialTheme.typography.bodySmall,
                    color = inputTextColor
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "(${product.discountText})",
                    fontSize = 10.sp,
                    color = errorColor
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // DELIVERY BADGE (NATURAL POSITION)
            Row(
                modifier = Modifier
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(20.dp))
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.arrive_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = product.deliveryText,
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }
    }
}




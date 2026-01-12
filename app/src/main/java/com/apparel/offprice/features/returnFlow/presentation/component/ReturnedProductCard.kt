package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.errorColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.returnFlow.data.ReturnItem

@Composable
fun ReturnedProductCard(
    product: ReturnItem
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, surfaceColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {

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
                        maxLines = 1,
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
                            text = "RRP ฿ ${product.rrp}",
                            style = MaterialTheme.typography.bodySmall,
                            color = inputTextColor
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "(${product.discount})",
                            fontSize = 10.sp,
                            color = errorColor
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // DELIVERY BADGE (NATURAL POSITION)
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.reason_label),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = product.reason,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            ReturnStatusRow(product.status)
        }
    }
}

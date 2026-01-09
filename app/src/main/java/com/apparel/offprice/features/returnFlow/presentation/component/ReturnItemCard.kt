package com.apparel.offprice.features.returnFlow.presentation.component

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.errorColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.features.returnFlow.data.ReturnItem
import com.apparel.offprice.features.returnFlow.data.ReturnStatus

@Composable
fun ReturnItemCard(
    item: ReturnItem,
    onClick: () -> Unit
) {

    val clipboardManager = LocalClipboardManager.current

    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Card(shape = RoundedCornerShape(5.dp)) {
                Row(modifier = Modifier.padding(3.dp)) {
                    Text(
                        text = "RETURN ID - ${item.returnId}",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(5.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.copy_icon), // your copy icon
                        contentDescription = "Copy return id",
                        modifier = Modifier
                            .padding(3.dp)
                            .clickable {
                                clipboardManager.setText(
                                    AnnotatedString(item.returnId)
                                )
                            }
                    )
                }

            }



            Spacer(Modifier.height(8.dp))

            Row( verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(productCardColor)
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {

                    // BRAND
                    Text(
                        text = item.brand,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // TITLE
                    Text(
                        text = item.title,
                        maxLines = 2,
                        color = inputTextColor,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "฿ ${item.price}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // PRICE ROW — SAME LINE (KEY FIX)
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = "RRP ฿ ${item.rrp}",
                            style = MaterialTheme.typography.bodySmall,
                            color = inputTextColor
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "(${item.discount})",
                            fontSize = 10.sp,
                            color = errorColor
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Reason: ${item.reason}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            ReturnStatusRow(item.status)
        }
    }
}

@Composable
fun ReturnStatusRow(status: ReturnStatus) {

    val (iconRes, text) = when (status) {
        ReturnStatus.REQUESTED -> R.drawable.green_approved_icon to "Return Requested"
        ReturnStatus.APPROVED -> R.drawable.green_approved_icon to "Approved"
        ReturnStatus.INITIATED -> R.drawable.yellow_initiated_icon to "Initiated"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.weight(1f)) // takes all remaining space

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ReturnItemCardPreview() {
    ReturnItemCard(
        item = ReturnItem(
            returnId = "#2966605041211678",
            brand = "ADIDAS",
            title = "Printed Shirt With Crew Neck",
            imageUrl = "",
            image = R.drawable.product_item_1,
            color = "Blue",
            size = "L",
            qty = 1,
            price = "₹35.00",
            rrp = "₹172.00",
            discount = "90% OFF",
            reason = "Received Wrong Item",
            status = ReturnStatus.REQUESTED
        ),
        onClick = {}
    )
}

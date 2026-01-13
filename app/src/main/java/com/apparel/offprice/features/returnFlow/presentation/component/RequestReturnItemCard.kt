package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.apparel.offprice.features.returnFlow.data.ReturnReason
import com.apparel.offprice.features.returnFlow.data.displayText

@Composable
fun RequestReturnItemCard(
    item: ReturnItem,
    selected: Boolean,
    selectedReason: ReturnReason?,
    onSelect: () -> Unit,
    onReasonClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelect() }, //WHOLE CARD CLICKABLE
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            if (selected) Color.Black else surfaceColor //BORDER CHANGE
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ✅ CHECKBOX (visual only)
            Checkbox(
                checked = selected,
                onCheckedChange = null //prevent double toggle
            )

            Spacer(modifier = Modifier.width(8.dp))

            // PRODUCT IMAGE
            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(productCardColor)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                // BRAND
                Text(
                    text = item.brand,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                // TITLE
                Text(
                    text = item.title,
                    maxLines = 1,
                    color = inputTextColor,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                // COLOR | QTY | SIZE
                Text(
                    text = "Color: ${item.color}   |   Qty: ${item.qty}   |   Size: ${item.size}",
                    color = inputTextColor,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 🔹 PRICE SECTION
                if (selected) {
                    //SELECTED → SAME ROW
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "฿ ${item.price}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.width(8.dp))

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
                } else {
                    // DESELECTED → RRP BELOW PRICE
                    Column {
                        Text(
                            text = "฿ ${item.price}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
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
                    }
                }


                // 🔹 REASON DROPDOWN (ONLY WHEN SELECTED)
                if (selected) {
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onReasonClick,
                        modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = selectedReason?.displayText()
                                ?: stringResource(R.string.label_select_reason),
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Icon(
                            painter = painterResource(R.drawable.down_arrow),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

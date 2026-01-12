package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.features.cart.data.CartProductItems
import com.apparel.offprice.features.myorder.data.OrderItem

@Composable
fun SingleItemRow(item: CartProductItems) {
    Row {
        Image(
            painter = painterResource(item.image),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(item.title, fontSize = 14.sp, style = MaterialTheme.typography.titleMedium)
            Text("Color: Blue  Qty: 01  Size: L", fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(text= ""+item.basePrice, style = MaterialTheme.typography.titleMedium)
                item.basePrice?.let {
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text= ""+it,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }

            item.discount?.let {
                Text(it, fontSize = 12.sp, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text("DELIVERY BY 06 NOV, THU", fontSize = 11.sp, color = Color.Green)
        }
    }
}

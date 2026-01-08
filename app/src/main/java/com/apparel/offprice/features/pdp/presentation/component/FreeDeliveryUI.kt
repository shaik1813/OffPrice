package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.saleCardColor


@Composable
fun FreeDeliveryUI() {

    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .background(Color(0xFFF3F3F3), RoundedCornerShape(8.dp))
                .padding(10.dp),

        ) {
            Text("100 % Authentic International \nbrands",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                fontSize = 10.sp,
                color = saleCardColor,
                modifier = Modifier.align(Alignment.TopStart))

            Image(painter = painterResource(R.drawable.brand_icon),
                contentDescription = null,
                modifier = Modifier.width(17.dp).height(20.dp).align(Alignment.BottomEnd))
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .background(Color(0xFFF3F3F3), RoundedCornerShape(8.dp))
                .padding(10.dp),

        ) {
            Text("Free Return \nWithin 14 days",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                fontSize = 10.sp,
                color = saleCardColor,
                modifier = Modifier.align(Alignment.TopStart))

            Image(painter = painterResource(R.drawable.product_returnicon),
                contentDescription = null,
                modifier = Modifier.width(20.dp).height(20.dp).align(Alignment.BottomEnd))
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .background(Color(0xFFF3F3F3), RoundedCornerShape(8.dp))
                .padding(10.dp),

        ) {
            Text("Free Delivery \nOn 200 AED \n+ Orders",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                fontSize = 10.sp,
                color = saleCardColor,
                modifier = Modifier.align(Alignment.TopStart))

            Image(painter = painterResource(R.drawable.delivery_aedicon),
                contentDescription = null,
                modifier = Modifier.width(25.dp).height(25.dp).align(Alignment.BottomEnd))
        }
    }

}
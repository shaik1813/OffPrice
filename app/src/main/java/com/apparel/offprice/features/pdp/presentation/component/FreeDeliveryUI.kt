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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R


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
                .height(80.dp)

                .background(Color(0xFFF3F3F3)
                    , RoundedCornerShape(8.dp)),

        ) {
            Text("100 % Authentic International brands", style = MaterialTheme.typography.titleMedium,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.TopStart).padding(10.dp))

            Image(painter = painterResource(R.drawable.brand_icon),
                contentDescription = null,
                modifier = Modifier.padding(10.dp).width(17.dp).height(20.dp).align(Alignment.BottomEnd))
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .background(Color(0xFFF3F3F3), RoundedCornerShape(8.dp)),

        ) {
            Text("100 % Authentic International brands", style = MaterialTheme.typography.titleMedium,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.TopStart).padding(10.dp))

            Image(painter = painterResource(R.drawable.brand_icon),
                contentDescription = null,
                modifier = Modifier.padding(10.dp).width(17.dp).height(20.dp).align(Alignment.BottomEnd))
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .background(Color(0xFFF3F3F3), RoundedCornerShape(8.dp)),

        ) {
            Text("100 % Authentic International brands", style = MaterialTheme.typography.titleMedium,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.TopStart).padding(10.dp))

            Image(painter = painterResource(R.drawable.brand_icon),
                contentDescription = null,
                modifier = Modifier.padding(10.dp).width(17.dp).height(20.dp).align(Alignment.BottomEnd))
        }
    }

}
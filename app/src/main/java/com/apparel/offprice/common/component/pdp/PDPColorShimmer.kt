package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg

@Composable
fun PDPColorShimmer(){
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        repeat(4) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(59.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(6.dp))
            )
        }
    }
}
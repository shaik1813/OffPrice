package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg

@Composable
fun HomeCategoryShimmer() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp,0.dp,5.dp,0.dp)){
        repeat(5) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(90.dp)
                    .padding(5.dp)
                    .background(ShimmerBg(), RoundedCornerShape(20)))
        }
    }

}
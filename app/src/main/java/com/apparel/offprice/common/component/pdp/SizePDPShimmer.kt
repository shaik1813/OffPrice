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
fun SizePDPShimmer() {

    Row(horizontalArrangement = Arrangement.spacedBy(13.dp)) {
        repeat(4){
        Box(
            modifier = Modifier
                .width(44.dp)
                .height(40.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(6.dp))
        )}

        Box(
            modifier = Modifier
                .width(53.dp)
                .height(40.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(6.dp))
        )

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(40.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(6.dp))
        )
    }
}
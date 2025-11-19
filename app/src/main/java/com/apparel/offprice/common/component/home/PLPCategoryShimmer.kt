package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg

@Composable
fun PLPCategoryShimmer() {
    Column() {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(40.dp)
            .padding(20.dp, 15.dp, 10.dp, 10.dp)
            .background(ShimmerBg(), RoundedCornerShape(50))
    )


    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
    ) {
        repeat(5) {
            Column() {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .padding(10.dp)
                        .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
                )

                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                        .padding(7.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(ShimmerBg(), RoundedCornerShape(20))
                )
            }

        }
    }
    }
}
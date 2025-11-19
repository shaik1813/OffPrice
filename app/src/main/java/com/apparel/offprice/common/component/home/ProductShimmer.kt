package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.component.shimmer.ShimmerBg

@Composable
fun ProductShimmer() {
    Column() {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(190.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.size(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 10.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )

        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(15.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
        )

        Box(
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
                .height(15.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
        )

        Box(
            modifier = Modifier
                .width(120.dp)
                .padding(10.dp)
                .height(15.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.size(20.dp))
    }
}
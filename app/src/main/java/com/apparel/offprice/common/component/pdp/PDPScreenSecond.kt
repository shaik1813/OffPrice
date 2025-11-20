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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg

@Preview(showBackground = true)
@Composable
fun PDPScreenSecond() {
    Column(modifier = Modifier.padding(20.dp).verticalScroll(rememberScrollState())) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)

        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(60.dp)
                    .height(15.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(40.dp)
                    .height(40.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(7.dp))
            )
        }

        Spacer(modifier = Modifier.size(6.dp))

        Box(
            modifier = Modifier
                .width(120.dp)
                .height(15.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.size(12.dp))

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(20.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.size(14.dp))

        Row() {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.size(25.dp))

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.size(20.dp))

            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(20.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Box(
            modifier = Modifier
                .width(130.dp)
                .height(20.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.size(20.dp))

/*
        Size section
*/
        SizePDPShimmer()

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

        Box(
            modifier = Modifier
                .padding(0.dp,20.dp,20.dp,0.dp)
                .width(screenWidth/1.5f)
                .height(10.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(6.dp))
        )

        Box(
            modifier = Modifier
                .padding(0.dp,20.dp,20.dp,0.dp)
                .width(screenWidth/1.6f)
                .height(10.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(6.dp))
        )

        Spacer(modifier = Modifier.size(20.dp))

        /*Color shimmer section*/
        PDPColorShimmer()


        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(60.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(15.dp))
        )

    }


}
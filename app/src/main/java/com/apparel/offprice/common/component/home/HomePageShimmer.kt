package com.apparel.offprice.common.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerEffect


@Preview(showBackground = true)
@Composable
fun HomePageShimmer() {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp,10.dp,20.dp,10.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            ShimmerEffect(
                modifier = Modifier.align(Alignment.CenterVertically)
                    .width(80.dp)
                    .height(20.dp)
                    .background(Color.LightGray, RoundedCornerShape(50)),
                durationMillis = 1000
            )
            Row(horizontalArrangement = Arrangement.End , modifier = Modifier.align(Alignment.CenterVertically)){
                ShimmerEffect(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(Color.LightGray, RoundedCornerShape(10)),
                    durationMillis = 1000
                )

                Spacer(modifier = Modifier.size(15.dp))

                ShimmerEffect(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(Color.LightGray, RoundedCornerShape(10)),
                    durationMillis = 1000
                )

                Spacer(modifier = Modifier.size(15.dp))

                ShimmerEffect(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(Color.LightGray, RoundedCornerShape(10)),
                    durationMillis = 1000
                )
            }

        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.White)
            )
        }
        Spacer(modifier= Modifier.size(20.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp)){
            repeat(5) {
                ShimmerEffect(
                    modifier = Modifier
                        .width(80.dp)
                        .height(50.dp)
                        .padding(10.dp)
                        .background(Color.LightGray, RoundedCornerShape(20)),
                    durationMillis = 1000
                )
            }
        }

        ShimmerEffect(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(170.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp)){
            repeat(5) {
                ShimmerEffect(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(10.dp)
                        .background(Color.LightGray, RoundedCornerShape(20)),
                    durationMillis = 1000
                )
            }
        }

        ShimmerEffect(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(110.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
        )

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp)){
            repeat(5) {
                ShimmerEffect(
                    modifier = Modifier
                        .width(250.dp)
                        .height(100.dp)
                        .padding(10.dp)
                        .background(Color.LightGray, RoundedCornerShape(20)),
                    durationMillis = 1000
                )
            }
        }



    }
}
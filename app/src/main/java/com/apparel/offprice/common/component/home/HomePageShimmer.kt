package com.apparel.offprice.common.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg
import com.example.myapplication.component.HomeCategoryShimmer
import com.example.myapplication.component.HomeShopShimmer


@Preview(showBackground = true)
@Composable
fun HomePageShimmer() {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        //Toolbar section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Box(
                modifier = Modifier.align(Alignment.CenterVertically)
                    .width(80.dp)
                    .height(20.dp)
                    .background(ShimmerBg(), RoundedCornerShape(50)))

            Row(horizontalArrangement = Arrangement.End , modifier = Modifier.align(Alignment.CenterVertically)){
                Box(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(ShimmerBg(), RoundedCornerShape(10)))

                Spacer(modifier = Modifier.size(15.dp))

                Box(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(ShimmerBg(), RoundedCornerShape(10)))

                Spacer(modifier = Modifier.size(15.dp))

                Box(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(ShimmerBg(), RoundedCornerShape(10)))
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


        //Category part
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp)){
            repeat(5) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(50.dp)
                        .padding(10.dp)
                        .background(ShimmerBg(), RoundedCornerShape(20)))
            }
        }

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(170.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp)){
            repeat(5) {
                HomeCategoryShimmer()
            }
        }

        //Banner part
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(110.dp)
                .background(ShimmerBg(), shape = RoundedCornerShape(12.dp))
        )


        //Coupon Offer
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(10.dp,0.dp,10.dp,0.dp)){
            repeat(5) {
                Box(
                    modifier = Modifier
                        .width(screenWidth/1.3f)
                        .height(100.dp)
                        .padding(10.dp)
                        .background(ShimmerBg(), RoundedCornerShape(20)),

                )
            }
        }


        //Shop by Brand
        HomeShopShimmer()

    }
}
package com.apparel.offprice.common.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg
import com.example.myapplication.component.PLPCategoryShimmer
import com.example.myapplication.component.ProductShimmer


@Preview(showBackground = true)
@Composable
fun PLPScreenShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(20.dp, 20.dp, 20.dp, 20.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(80.dp)
                    .height(20.dp)
                    .background(ShimmerBg(), shape = RoundedCornerShape(20.dp))
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(ShimmerBg(), RoundedCornerShape(10))
                )

                Spacer(modifier = Modifier.size(15.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                        .background(ShimmerBg(), RoundedCornerShape(10))
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


        PLPCategoryShimmer()


        Spacer(modifier = Modifier.size(20.dp))

        Box(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(10.dp))

        val list = (1..10).map { it.toString() }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(178.dp),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 10.dp,
                end = 12.dp,
                bottom = 10.dp
            ),
            content = {
                items(list.size) { index ->
                    ProductShimmer()
                }
            }
        )

    }
}
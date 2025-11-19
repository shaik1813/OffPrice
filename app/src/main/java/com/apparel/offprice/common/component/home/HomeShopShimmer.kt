package com.example.myapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.shimmer.ShimmerBg
import kotlin.collections.map

@Composable
fun HomeShopShimmer() {
    val list = (1..6).map { it.toString() }

    LazyHorizontalGrid (
        rows = GridCells.Fixed(1),
        modifier = Modifier.height(150.dp),   // required

        // content padding
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 10.dp
        ),
        content = {
            items(list.size) { index ->

                Box(modifier = Modifier.height(100.dp).width(120.dp).padding(10.dp).background(ShimmerBg(),shape = RoundedCornerShape(15.dp))){

                }
            }
        }
    )

}
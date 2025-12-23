package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.carousel.ImageSliderWithIndicatorPLP

@Composable
fun CategoriesBanner(
    banners: List<Int>,
    modifier: Modifier = Modifier
) {
    ImageSliderWithIndicatorPLP(
        images = banners,
        selectedIndicatorColor = Color.White,
        unSelectedIndicatorColor = Color.LightGray,
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp)),
        placeholder = R.drawable.icon_successful
    )
}

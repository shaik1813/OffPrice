package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.carousel.ImageSliderWithIndicatorPDP
import com.apparel.offprice.common.theme.OffPriceTheme
import com.apparel.offprice.features.pdp.presentation.component.ProductDescSection
import com.apparel.offprice.features.pdp.presentation.component.ProductImageSection


@Preview(showBackground = true)
@Composable
fun PDPscreen() {

    OffPriceTheme {
        Column(modifier = Modifier.statusBarsPadding()) {
            ProductImageSection()

            ProductDescSection()
        }
    }

}
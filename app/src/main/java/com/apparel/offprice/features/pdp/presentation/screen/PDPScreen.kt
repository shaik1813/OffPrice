package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apparel.offprice.features.pdp.presentation.component.ProductDescSection
import com.apparel.offprice.features.pdp.presentation.component.ProductImageSection


@Preview(showBackground = true)
@Composable
fun PDPscreen() {

    Column(modifier = Modifier.statusBarsPadding()) {
        ProductImageSection()

        ProductDescSection()
    }

}
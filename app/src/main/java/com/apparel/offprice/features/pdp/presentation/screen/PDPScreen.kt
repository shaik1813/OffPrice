package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apparel.offprice.features.pdp.presentation.component.ProductDescSection
import com.apparel.offprice.features.pdp.presentation.component.ProductImageSection


@Preview(showBackground = true)
@Composable
fun PDPscreen() {

        LazyColumn(modifier = Modifier.systemBarsPadding()) {

            item { ProductImageSection() }

            item { ProductDescSection() }
        }

}
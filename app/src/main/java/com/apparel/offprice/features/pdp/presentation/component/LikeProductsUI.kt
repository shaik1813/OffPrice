package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.features.pdp.data.model.productList
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems


@Composable
fun LikeProductsUI() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(com.apparel.offprice.R.string.you_may_also_like),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 14.sp,
            color = loginButtonColor
        )

        Text(
            text = stringResource(com.apparel.offprice.R.string.view_all),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = loginButtonColor
        )
    }

    Spacer(modifier = Modifier.size(16.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(productList) { product ->
            ProductCardForLike(
                product = ProductCardItems(
                    id = "1",
                    tag = "GOLD LABEL",
                    image = listOf(R.drawable.colorimg),
                    brand = "Nike",
                    title = "Nike Air Max 270 React ENG",
                    sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
                    basePrice = "139.99",
                    discountPrice = "",
                    rrp = "159.99",
                    discount = "20",
                    delivery = "GET IT IN 90M",
                    isWishlist = false
                ),
                onWishlistClick = {}
            )
        }
    }

}

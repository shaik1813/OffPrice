package com.apparel.offprice.features.pdp.presentation.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.features.plp.data.model.ProductCardItems
import com.apparel.offprice.features.plp.data.model.sampleProducts


@Composable
fun RecentViewProducts(onWishlistClick: (ProductCardItems) -> Unit, onProductClick: (ProductCardItems) -> Unit) {

    val heightOfList = LocalConfiguration.current.screenWidthDp.dp - 44.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(com.apparel.offprice.R.string.recent_viewed),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 14.sp,
            color = saleCardColor
        )

        Text(
            text = stringResource(com.apparel.offprice.R.string.view_all),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 14.sp,
            color = loginButtonColor
        )
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sampleProducts) { product ->
            // Each card takes full width inside its column
            SimilarProductCard(
                heightOfList,
                product = product,
                onWishlistClick = { onWishlistClick(product) },
                modifier = Modifier
                    .clickable { onProductClick(product) }
            )
        }
    }

    Spacer(modifier = Modifier.size(90.dp))
}

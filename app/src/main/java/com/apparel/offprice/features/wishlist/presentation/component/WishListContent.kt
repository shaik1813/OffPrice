package com.apparel.offprice.features.wishlist.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.plp.presentation.screens.ProductCard
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems


@Composable
fun WishList(
    wishlistItem: List<ProductCardItems>,
    addToCartItem: (ProductCardItems) -> Unit,
    onNavigateToPDP: (ProductCardItems) -> Unit,
    onWishListClicked: (ProductCardItems) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(wishlistItem) { product ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProductCard(
                    product = product.copy(isWishlist = true),
                    onWishlistClick = {
                        onWishListClicked(product)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onNavigateToPDP(product)
                        }
                )
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { addToCartItem(product) },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_add_to_bag).uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 12.sp,
                            letterSpacing = 0.2.sp
                        ),
                        color = Color.Black
                    )
                }
            }
        }
    }
}
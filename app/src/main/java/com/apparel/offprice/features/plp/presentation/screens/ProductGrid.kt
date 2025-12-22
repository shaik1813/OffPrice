package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductGrid(
    products: List<ProductCardItems>,
    onWishlistClick: (ProductCardItems) -> Unit = {},
    onProductClick: (ProductCardItems) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(products, key = { it.id}) { product ->

            // Each card takes full width inside its column
            ProductCard(
                product = product,
                onWishlistClick = { onWishlistClick(product) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onProductClick(product) }
            )
        }
    }
}

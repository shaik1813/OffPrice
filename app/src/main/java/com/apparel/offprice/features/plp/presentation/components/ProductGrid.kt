package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.utils.Constant.INLINE_FILTER_DISPLAY
import com.apparel.offprice.common.utils.Constant.PRODUCT_DISPLAY_GRID
import com.apparel.offprice.features.plp.data.model.FilterGroup
import com.apparel.offprice.features.plp.data.model.FilterItem
import com.apparel.offprice.features.plp.data.model.FilterType
import com.apparel.offprice.features.plp.data.model.ProductCardItems

@Composable
fun ProductGrid(
    products: List<ProductCardItems>,
    gridState: LazyGridState,
    inlineFilters: List<FilterGroup>,
    onFilterClick: (FilterType, FilterItem) -> Unit,
    onWishlistClick: (ProductCardItems) -> Unit,
    onProductClick: (ProductCardItems) -> Unit
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(PRODUCT_DISPLAY_GRID),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Products before Inline Filter
        items(
            items = products.take(INLINE_FILTER_DISPLAY),
            key = { it.id }
        ) { product ->
            // Each card takes full width inside its column
            ProductCard(
                product = product,
                onWishlistClick = { onWishlistClick(product) },
                modifier = Modifier
                    .clickable { onProductClick(product) }
            )
        }
        // Full-width filter row
        if (products.size > INLINE_FILTER_DISPLAY) {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                InlineFilterRow(
                    filters = inlineFilters,
                    onFilterItemClick = { filterType, filterItem ->
                        onFilterClick(filterType, filterItem)
                    }
                )
            }
        }
        // Products after Inline Filter
        items(
            items = products.drop(INLINE_FILTER_DISPLAY),
            key = { it.id }
        ) { product ->
            ProductCard(
                product = product,
                onWishlistClick = { onWishlistClick(product) },
                modifier = Modifier.clickable {
                    onProductClick(product)
                }
            )
        }

    }
}


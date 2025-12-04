package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.plp.data.model.samplePLPHorizontalListItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PLPScreen(onNavigateToSearch: () -> Unit,
              onNavigateToWishlist: () -> Unit) {

    var selectedCategoryId by remember { mutableStateOf("1") }

    // Bottom Sheet State
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true     // ⬅️ CRITICAL
    )
    var isFilterSheetOpen by remember { mutableStateOf(false) }

    // SORT SHEET STATE
    val sortSheetState = rememberModalBottomSheetState()
    var isSortSheetOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.label_off_price).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 4.dp)
                )
            },
            actions = {
                IconButton(
                    onClick = onNavigateToSearch,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = onNavigateToWishlist,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_wishlist),
                        contentDescription = "Wishlist",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            windowInsets = WindowInsets(0, 0, 0, 0),
            modifier = Modifier
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color.Gray
                ),
        )
        HorizontalDivider(thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Text(
                text = "Men's New Arrivals ",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
            )
            Text(
                text = "(150 items)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Thin
                ),
                modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
            )
        }

        PLPCategoryHorizontalList(
            categories = samplePLPHorizontalListItems,
            selectedCategoryId = selectedCategoryId,
            onCategoryClick = { clickedItem ->
                selectedCategoryId = clickedItem.id
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        FilterStrip(
            onFilterClick = { isFilterSheetOpen = true },
            onSortClick = { isSortSheetOpen = true }
        )

        ProductGrid(
            products = sampleProducts,
            onWishlistClick = { clickedProduct ->
                // handle wishlist toggle
            },
            onProductClick = { clickedProduct ->
                // navigate to product details
            }
        )

        // Bottom Sheet (filter)
        if (isFilterSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { isFilterSheetOpen = false },
                dragHandle = null,  // remove handle (matches your figma)
                modifier = Modifier.fillMaxHeight()  // ⬅️ FORCE FULL SCREEN HEIGHT
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()    // ⬅️ Force content to full height
                        .fillMaxWidth()
                ) {
                    FilterScreen(
                        onClose = { isFilterSheetOpen = false },
                        onApply = { isFilterSheetOpen = false },
                        onClearAll = {
                            // TODO: clear all selected filters from ViewModel/State
                        }
                    )
                }
            }
        }

        //   SORT BOTTOM SHEET Hosted Here
        if (isSortSheetOpen) {
            ModalBottomSheet(
                sheetState = sortSheetState,
                onDismissRequest = { isSortSheetOpen = false },
                dragHandle = null, // remove default handle
                containerColor = Color.White,
                tonalElevation = 0.dp
            ) {
                SortBottomSheet(
                    onClose = { isSortSheetOpen = false },
                    onSortSelected = { sortType ->
                        // TODO: handle sort action
                        isSortSheetOpen = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PLPScreenPreview(){
    PLPScreen(onNavigateToSearch = {}, onNavigateToWishlist = {})
}
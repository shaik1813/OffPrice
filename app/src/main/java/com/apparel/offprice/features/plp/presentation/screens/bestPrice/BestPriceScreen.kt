package com.apparel.offprice.features.plp.presentation.screens.bestPrice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.plp.presentation.components.ShowFilterBottomSheet
import com.apparel.offprice.features.plp.presentation.components.ShowSortBottomSheet
import com.apparel.offprice.features.plp.presentation.components.FilterStrip
import com.apparel.offprice.features.plp.presentation.components.PLPCategoryHorizontalList
import com.apparel.offprice.features.plp.presentation.components.ProductGrid
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.runtime.collectAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BestPriceScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onNavigateToPDP: (String) -> Unit,
    viewModel: BestPriceViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)
    val gridState = rememberLazyGridState()
    val brandCount = viewModel.brandSelectedCount.collectAsState()
    val sizeCount = viewModel.sizeSelectedCount.collectAsState()

    effect.CollectInLaunchedEffect {
        when (it) {
            BestPriceContract.UiEffect.NavigateToSearch -> onNavigateToSearch()
            BestPriceContract.UiEffect.NavigateToWishlist -> onNavigateToWishlist()
            BestPriceContract.UiEffect.ScrollToTop -> {
                gridState.animateScrollToItem(0)
            }
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                viewModel.onGridScroll(index)
            }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.label_best_price).uppercase(),
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Text(
                            text = "156 Items",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 4.dp),
                            color = nonreturnTxtColor
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = borderColor,
                                shape = CircleShape
                            )
                            .clickable {
                                event.invoke(BestPriceContract.UiEvent.NavigateToSearch)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = borderColor,
                                shape = CircleShape
                            )
                            .clickable {
                                event.invoke(BestPriceContract.UiEvent.NavigateToWishlist)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_wishlist),
                            contentDescription = "WishList",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.shadow(
                    elevation = 6.dp,
                    spotColor = Color.Black
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentWindowInsets = WindowInsets(bottom = 0),
        floatingActionButton = {
            AnimatedVisibility(
                visible = state.showScrollToTop,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = { it / 2 }
                ),
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = { it / 2 }
                )
            ) {
                FloatingActionButton(
                    onClick = {
                        event(BestPriceContract.UiEvent.ScrollToTopClicked)
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll to top",
                        tint = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(Modifier.height(20.dp))
            PLPCategoryHorizontalList(
                categories = state.lTwoCategoryList,
                selectedCategoryId = state.selectedCategoryId,
                onCategoryClick = { clickedItem ->
                    event.invoke(BestPriceContract.UiEvent.CategorySelected(clickedItem))
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            FilterStrip(
                brandCount = brandCount.value,
                sizeCount = sizeCount.value,
                onFilterClick = {
                    event.invoke(BestPriceContract.UiEvent.FilterSheetOpen(it))
                },
                onSortClick = {
                    event.invoke(BestPriceContract.UiEvent.ToggleSortSheet)
                }
            )
            ProductGrid(
                products = state.productList,
                gridState = gridState,
                inlineFilters = state.inlineFilters,
                onFilterClick = { filterType, filterItem ->
                    event.invoke(BestPriceContract.UiEvent.OnInlineFilterItemClicked(filterType, filterItem))
                },
                onWishlistClick = { clickedProduct ->
                    event.invoke(BestPriceContract.UiEvent.WishlistClicked(clickedProduct))
                },
                onProductClick = { clickedProduct ->
                    onNavigateToPDP(clickedProduct.id)
                }
            )

            if (state.isFilterSheetOpen) {
                ShowFilterBottomSheet(
                    state = state,
                    onEvent = {
                        event.invoke(it)
                    },
                    onDismiss = {
                        event.invoke(BestPriceContract.UiEvent.FilterSheetClosed)
                    })
            }

            if (state.isSortSheetOpen) {
                ShowSortBottomSheet(
                    sortList = state.sortList,
                    onEvent = { event.invoke(it) },
                    onDismiss = {
                        event.invoke(BestPriceContract.UiEvent.ToggleSortSheet)
                    }
                )
            }
        }
    }
}
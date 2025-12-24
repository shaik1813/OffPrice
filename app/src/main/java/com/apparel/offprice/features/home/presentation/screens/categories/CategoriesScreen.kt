package com.apparel.offprice.features.home.presentation.screens.categories


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CategoriesList
import com.apparel.offprice.features.home.presentation.component.CategoryTabsWithIndicator
import com.apparel.offprice.features.home.presentation.component.CircleIconButton
import com.apparel.offprice.features.home.presentation.component.FlashSaleBanner
import com.apparel.offprice.features.home.presentation.component.sampleCategoryBannerImages
import com.apparel.offprice.features.home.presentation.component.sampleCategoryList
import com.apparel.offprice.routes.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onNavigateToSubCategory: (String) -> Unit,
    viewModel: CategoriesViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            CategoriesContract.UiEffect.NavigateToHome -> {
                //onNavigateToBack()
            }

            CategoriesContract.UiEffect.NavigateToSearch -> {
                onNavigateToSearch()
            }

            CategoriesContract.UiEffect.NavigateToWishlist -> {
                onNavigateToWishlist()
            }

            is CategoriesContract.UiEffect.NavigateToSubCategory -> {
                onNavigateToSubCategory(it.title)
            }

            is CategoriesContract.UiEffect.ShowError -> {
                TODO()
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.label_categories),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            },
            actions = {
                CircleIconButton(
                    icon = R.drawable.icon_search,
                    contentDescription = "Search",
                    onClick = {
                        event(CategoriesContract.UiEvent.NavigateToSearch)
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))

                CircleIconButton(
                    icon = R.drawable.icon_wishlist,
                    contentDescription = "Wishlist",
                    badgeCount = 4,
                    onClick = {
                        event(CategoriesContract.UiEvent.NavigateToWishlist)

                    }
                )

                Spacer(modifier = Modifier.width(12.dp))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            windowInsets = WindowInsets(0, 0, 0, 0)
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        CategoryTabsWithIndicator(
            categories = state.selectedCategory,
            isHome = false,
            onCategorySelected = {
                event.invoke(CategoriesContract.UiEvent.OnCategorySelected(it))
            }
        )

        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
        )

        FlashSaleBanner(
            images = sampleCategoryBannerImages
        )

        Spacer(modifier = Modifier.height(16.dp))

        CategoriesList(
            list = sampleCategoryList,
            onItemClick = { item ->
                // Handle item click
                event(CategoriesContract.UiEvent.OnNavigateToSubCategory(item.title))
            }
        )
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen(
        onNavigateToSearch = {},
        onNavigateToWishlist = {},
        onNavigateToSubCategory = {},
        viewModel = viewModel()
    )
}

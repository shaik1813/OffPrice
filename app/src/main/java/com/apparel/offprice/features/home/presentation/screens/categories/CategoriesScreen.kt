package com.apparel.offprice.features.home.presentation.screens.categories


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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CategoriesList
import com.apparel.offprice.features.home.presentation.component.FlashSaleBanner
import com.apparel.offprice.features.home.presentation.screens.home.CategoryPrimaryScrollableTabs

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.label_categories).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
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
                                event.invoke(CategoriesContract.UiEvent.NavigateToSearch)
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
                                event.invoke(CategoriesContract.UiEvent.NavigateToWishlist)
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
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            if (state.lOneCategoryList.isNotEmpty() && state.selectedIndex >= 0) {
                CategoryPrimaryScrollableTabs(
                    categories = state.lOneCategoryList,
                    selectedIndex = state.selectedIndex,
                    isHome = false,
                    onTabSelected = { index, item ->
                        event.invoke(CategoriesContract.UiEvent.OnCategorySelected(index, item))
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (state.bannerList.isNotEmpty()) {
                FlashSaleBanner(
                    images = state.bannerList.map { it.image },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CategoriesList(
                list = state.categoryList,
                onItemClick = { item ->
                    event(CategoriesContract.UiEvent.OnNavigateToSubCategory(item.title))
                }
            )
        }
    }
}

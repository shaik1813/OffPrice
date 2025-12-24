package com.apparel.offprice.features.home.presentation.screens.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.IconBackgroundColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CategoriesList
import com.apparel.offprice.features.home.presentation.component.CategoryListItem
import com.apparel.offprice.features.home.presentation.component.CircleIconButton
import com.apparel.offprice.features.home.presentation.component.sampleSubCategoryList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCategoryScreen(
    title: String,
    onNavigateToSearch: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onBack: () -> Unit,
    onNavigateToPLP: (String) -> Unit,
    viewModel: SubCategoryViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel)

    LaunchedEffect(Unit) {
        //viewModel.loadSubCategories(parentCategory)
    }

    effect.CollectInLaunchedEffect {
        when (it) {
            SubCategoryContract.UiEffect.NavigateBack -> onBack()

            is SubCategoryContract.UiEffect.NavigateToPLP -> {
                onNavigateToPLP(it.categoryId)
            }

            SubCategoryContract.UiEffect.NavigateToSearch -> {
                onNavigateToSearch()
            }

            SubCategoryContract.UiEffect.NavigateToWishlist -> {
                onNavigateToWishlist()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    event(SubCategoryContract.UiEvent.OnBackClicked)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back"
                    )
                }
            },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            actions = {
                CircleIconButton(
                    icon = R.drawable.icon_search,
                    contentDescription = "Search",
                    onClick = {
                        event(SubCategoryContract.UiEvent.NavigateToSearch)
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
                CircleIconButton(
                    icon = R.drawable.icon_wishlist,
                    contentDescription = "Wishlist",
                    badgeCount = 4,
                    onClick = {
                        event(SubCategoryContract.UiEvent.NavigateToWishlist)
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            windowInsets = WindowInsets(0, 0, 0, 0)
        )

        Spacer(modifier = Modifier.height(6.dp))

        HorizontalDivider(thickness = 1.dp, color = Color(0xFFF0F0F0))

        // SUBCATEGORY LIST (REUSED)
        CategoriesList(
            list = sampleSubCategoryList,
            onItemClick = {
                event(SubCategoryContract.UiEvent.OnSubCategoryClicked(it))
            }
        )
    }
}

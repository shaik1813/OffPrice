package com.apparel.offprice.features.home.presentation.screens.subcategory

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.apparel.offprice.features.home.presentation.component.CategoriesList
import com.apparel.offprice.features.home.presentation.component.CircleIconButton
import com.apparel.offprice.features.home.presentation.component.sampleSubCategoryList
import com.apparel.offprice.features.plp.presentation.screens.bestPrice.BestPriceContract

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

    effect.CollectInLaunchedEffect {
        when (it) {
            SubCategoryContract.UiEffect.NavigateBack -> onBack()

            is SubCategoryContract.UiEffect.NavigateToPLP -> {
                onNavigateToPLP(it.category)
            }

            SubCategoryContract.UiEffect.NavigateToSearch -> {
                onNavigateToSearch()
            }

            SubCategoryContract.UiEffect.NavigateToWishlist -> {
                onNavigateToWishlist()
            }
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
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
                                event.invoke(SubCategoryContract.UiEvent.NavigateToSearch)
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
                                event.invoke(SubCategoryContract.UiEvent.NavigateToWishlist)
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
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // SUBCATEGORY LIST (REUSED)
            CategoriesList(
                list = state.subCategories,
                onItemClick = {
                    event(SubCategoryContract.UiEvent.OnSubCategoryClicked(it))
                }
            )
        }
    }
}

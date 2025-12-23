package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.welcome.presentation.component.GenderCategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderCategoryScreen(
    onCategoryClick: () -> Unit,
    onSearchClick: () -> Unit,
    onWishlistClick: () -> Unit,
    viewModel: GenderCategoryViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            GenderCategoryContract.UiEffect.OnNavigateToNextScreen -> onCategoryClick()
            GenderCategoryContract.UiEffect.OnNavigateToSearchScreen -> onSearchClick()
            GenderCategoryContract.UiEffect.OnNavigateToWishListScreen -> onWishlistClick()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.offprice_logo),
                        contentDescription = "App name",
                        modifier = Modifier
                            .size(width = 100.dp, height = 21.dp)
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
                                event.invoke(GenderCategoryContract.UiEvent.OnSearchClicked)
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
                                event.invoke(GenderCategoryContract.UiEvent.OnWishListClicked)
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
            .padding(WindowInsets.safeDrawing.asPaddingValues())

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            // Title
            Text(
                text = stringResource(R.string.label_exclusive_collection),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subtitle
            Text(
                text = stringResource(R.string.label_shop_by_category),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = nonreturnTxtColor,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Category List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.genderList, key = { it.id }) { item ->
                    GenderCategoryCard(item) {
                        event.invoke(GenderCategoryContract.UiEvent.OnGenderSelected(it))
                    }
                }
            }
        }
    }
}

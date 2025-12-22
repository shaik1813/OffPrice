package com.apparel.offprice.features.home.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CategoryTabsWithIndicator

@Composable
fun HomeContent(
    onNavigateToSearch: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            is HomeContract.UiEffect.ShowMessage -> {

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush =
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xF13B292A),
                            Color(0XFF9F1D26)
                        ),
                    )
            )
    ) {
        Spacer(modifier = Modifier.height(42.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.83f)
                    .height(42.dp)
                    .clickable { onNavigateToSearch() },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xE5F5F5F5)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = "Search",
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.label_search),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = Color(0xFF575959)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0XF5F5F5E5))
                    .clickable { onNavigateToWishlist() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_wishlist),
                    contentDescription = "Wishlist",
                    modifier = Modifier.size(20.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        CategoryTabsWithIndicator(
            categories = state.lOneCategoryItems,
            isHome = true,
            onCategorySelected = {
                event.invoke(HomeContract.UiEvent.OnLOneCategoryItemClick(it))
            }
        )
    }

}



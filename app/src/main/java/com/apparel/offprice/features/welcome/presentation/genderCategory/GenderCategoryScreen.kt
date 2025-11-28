package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.features.welcome.data.model.genderCategories

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderCategoryScreen(
    onCategoryClick: () -> Unit,
    onSearchClick: () -> Unit,
    onWishlistClick: () -> Unit,
    viewModel: GenderCategoryViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = WindowInsets.safeDrawing.asPaddingValues())
    ) {

        TopAppBar(
            title = {
                Image(
                    painter = painterResource(R.drawable.icon_off_price),
                    contentDescription = "App name"
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        onSearchClick()
                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = {
                        onWishlistClick()
                    },
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

        Spacer(modifier = Modifier.height(20.dp))


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
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
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Category List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(genderCategories) { item ->
                    GenderCategoryCard(item) {
                        viewModel.saveGender(item)
                        onCategoryClick()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenderCategoryScreenPreview() {
    GenderCategoryScreen(onCategoryClick = {}, onSearchClick = {}, onWishlistClick = {})
}

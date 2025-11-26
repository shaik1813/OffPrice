package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.apparel.offprice.features.welcome.data.model.GenderCategoryItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.apparel.offprice.R
import com.apparel.offprice.features.home.presentation.screens.home.CardIcon
import com.apparel.offprice.features.welcome.data.model.genderCategories
import com.apparel.offprice.routes.AppScreen

@Composable
fun GenderCategoryScreen(
    onCategoryClick: (GenderCategoryItem) -> Unit,
    onSearchClick: () -> Unit = {},
    onWishlistClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(1f)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "OFF/PRICE",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 12.dp)
            )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CardIcon(
                icon = R.drawable.icon_wishlist,
                iconDescriptor = "Wishlist"
            ){
                onWishlistClick()
            }
            CardIcon(
                icon = R.drawable.icon_search,
                iconDescriptor = "Icon search"
            ){
                onSearchClick()
            }
        }
    }
    HorizontalDivider(thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // Title
            Text(
                text = "Discover Our Exclusive Collection",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subtitle
            Text(
                text = "Shop by category and choose what you love",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Category List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(genderCategories) { item ->
                    GenderCategoryCard(item) {
                        onCategoryClick(item)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenderCategoryScreenPreview(){
    GenderCategoryScreen(onCategoryClick = {})
}

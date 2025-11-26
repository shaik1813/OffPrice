package com.apparel.offprice.features.home.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.IconBackgroundColor

@Composable
fun HomeContent(
    onNavigateToSearch: () -> Unit,
    onNavigateToStore: () -> Unit,
    onNavigateToWishlist: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
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
                    icon = R.drawable.icon_store_locator,
                    iconDescriptor = "Store Locator"
                ){
                    onNavigateToStore()
                }
                CardIcon(
                    icon = R.drawable.icon_wishlist,
                    iconDescriptor = "Wishlist"
                ){
                    onNavigateToWishlist()
                }
                CardIcon(
                    icon = R.drawable.icon_search,
                    iconDescriptor = "Icon search"
                ){
                    onNavigateToSearch()
                }
            }
        }
        HorizontalDivider(thickness = 1.dp)
    }
}


@Composable
fun CardIcon(
    icon : Int,
    iconDescriptor : String,
    onClick : () -> Unit
){
    IconButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(6.dp)
            .background(
                color = IconBackgroundColor,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = iconDescriptor,
            modifier = Modifier
                .size(24.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(onNavigateToSearch = {}, onNavigateToStore = {}, onNavigateToWishlist = {})
}
package com.apparel.offprice.features.home.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            Image(
                painter = painterResource(R.drawable.offprice_pics),
                contentDescription = "App Logo pics",
                modifier = Modifier
                    .size(100.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {
                        onNavigateToStore()
                    },
                    modifier = Modifier
                        .padding(6.dp)
                        .background(
                            color = IconBackgroundColor,
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_store_locator),
                        contentDescription = "store Locator",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                IconButton(
                    onClick = {
                        onNavigateToWishlist()
                    },
                    modifier = Modifier
                        .padding(6.dp)
                        .background(
                            color = IconBackgroundColor,
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_wishlist),
                        contentDescription = "wishlist",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }

                IconButton(
                    onClick = {
                        onNavigateToSearch()
                    },
                    modifier = Modifier
                        .padding(6.dp)
                        .background(
                            color = IconBackgroundColor,
                            shape = MaterialTheme.shapes.small
                        ),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = "icon search",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }
    }
}

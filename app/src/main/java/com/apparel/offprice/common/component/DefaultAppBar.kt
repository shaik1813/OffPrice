package com.apparel.offprice.common.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
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
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    title:String,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 4.dp),
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onBackPressed() },
            )
        },
        actions = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "English",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 14.sp
                    ),
                    color = Color.Black
                )
                VerticalDivider(
                    modifier = Modifier
                        .height(16.dp)
                        .padding(horizontal = 4.dp)
                )
                Text(
                    text = "العربية",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp
                    )
                )
            }
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBarWithAction(
    title:String,
    onBackPressed: () -> Unit,
    onSearchClicked : () -> Unit,
    onWishlistClicked : () -> Unit,
){
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 4.dp)
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onBackPressed() },
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
                        onSearchClicked()
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
                        onWishlistClicked()
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
}
package com.apparel.offprice.features.wishlist.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@Composable
fun EmptyWishListScreen(
    onStartShoppingClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon_empty_wishlist),
            contentDescription = "WishList"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.text_empty_wishlist),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.text_empty_wishlist_description),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            onClick = { onStartShoppingClicked() },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.btn_start_shopping).uppercase(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }
    }
}
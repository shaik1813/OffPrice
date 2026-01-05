package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.surfaceColor

@Composable
fun CategoryListRow(
    item: CategoryListItem,
    onClick: (CategoryListItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item) }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // LEFT CIRCLE IMAGE
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(surfaceColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // TITLE
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )

        // RIGHT ARROW
        Icon(
            painter = painterResource(id = R.drawable.arrow_right_grey_icon),
            contentDescription = "Next"
        )
    }
}

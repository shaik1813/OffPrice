package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.features.plp.data.model.LTwoCategoryListItem

@Composable
fun PLPCategoryHorizontalList(
    categories: List<LTwoCategoryListItem>,
    selectedCategoryId: Int,
    onCategoryClick: (LTwoCategoryListItem) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {

        items(categories, key = { it.id }) { item ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onCategoryClick(item) }
            ) {

                // Grey rounded icon box
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(item.image),
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if (item.id == selectedCategoryId) FontWeight.W700 else FontWeight.W500,
                    color = if (item.id == selectedCategoryId) MaterialTheme.colorScheme.primary else buttonBorderColor,
                    modifier = Modifier
                        .padding(top = 2.dp),
                    textDecoration = if (item.id == selectedCategoryId) TextDecoration.Underline else TextDecoration.None
                )
            }
        }
    }
}

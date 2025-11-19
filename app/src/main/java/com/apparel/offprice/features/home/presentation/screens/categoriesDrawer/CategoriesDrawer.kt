package com.apparel.offprice.features.home.presentation.screens.categoriesDrawer


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import com.apparel.offprice.features.home.data.model.CategoryItem
import com.apparel.offprice.features.home.data.model.sampleCategoryItems
import com.apparel.offprice.features.home.data.model.sampleTopTabs

@Composable
fun CategoriesDrawer(
    onClose: () -> Unit,
    onItemClick: (CategoryItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "OFF/PRICE",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close,
                    contentDescription = "Close", tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(8.dp).fillMaxWidth())

        // Horizontal tabs
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(sampleTopTabs) { topTabs ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFEAEAEA))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(topTabs.label)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp)
            .fillMaxWidth())

        // Category list
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(sampleCategoryItems) { item ->
                CategoryCard(item, onItemClick)
            }
        }
    }
}

@Preview
@Composable
fun CategoriesDrawerPreview(){
    CategoriesDrawer(
        onClose = {},
        onItemClick = {}
    )
}

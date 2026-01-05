package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesList(
    list: List<CategoryListItem>,
    onItemClick: (CategoryListItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { item ->
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.surface
            )
            CategoryListRow(
                item = item,
                onClick = onItemClick
            )
        }
    }
}

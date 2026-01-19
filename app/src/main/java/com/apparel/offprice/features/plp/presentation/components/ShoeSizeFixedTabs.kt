package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.features.home.data.model.LOneCategoryItem

@Composable
fun ShoeSizeFixedTabs(
    categories: List<LOneCategoryItem>,
    selectedIndex: Int,
    onTabSelected: (index: Int, item: LOneCategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier,
        containerColor = Color.Transparent,
        indicator = { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                color = MaterialTheme.colorScheme.secondary
            )
        },
        divider = {
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE5E5E5)
            )
        }
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onTabSelected(index, category) },
                text = {
                    Text(
                        text = category.title.uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                        color =
                            if (selectedIndex == index)
                                MaterialTheme.colorScheme.secondary
                            else
                                MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    }
}

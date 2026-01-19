package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.features.home.data.model.LOneCategoryItem
import com.apparel.offprice.features.home.presentation.screens.home.CategoryPrimaryScrollableTabs
import com.apparel.offprice.features.plp.data.model.FilterGroup
import com.apparel.offprice.features.plp.data.model.FilterType
import com.apparel.offprice.features.plp.data.model.ShoeSizeRegion

@Composable
fun ShoeSizeFilterContent(
    filterGroup: FilterGroup,
    onFilterClick: (FilterType, String) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val shoeTabs = listOf(
        LOneCategoryItem(id = 1, title = "EU"),
        LOneCategoryItem(id = 2, title = "UK"),
        LOneCategoryItem(id = 3, title = "US")
    )

    val filteredSizes = when (selectedTabIndex) {

        // EU sizes → integers & fractions (40, 41⅓, 42)
        0 -> filterGroup.items.filter {
            it.name.any { ch -> ch.isDigit() } && it.name.length >= 2
        }

        // UK sizes → small decimals (6, 6.5, 7)
        1 -> filterGroup.items.filter {
            it.name.toFloatOrNull()?.let { size -> size in 5f..9f } == true
        }

        // US sizes → larger decimals (7, 7.5, 8, 9, 10)
        else -> filterGroup.items.filter {
            it.name.toFloatOrNull()?.let { size -> size in 7f..13f } == true
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        // 🔹 Tabs
        ShoeSizeFixedTabs(
            categories = shoeTabs,
            selectedIndex = selectedTabIndex,
            onTabSelected = { index, _ ->
                selectedTabIndex = index
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Sizes
        LazyColumn {
            items(filteredSizes) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                ) {

                    Image(
                        painter =
                            if (item.isSelected)
                                painterResource(R.drawable.icon_checked_checkbox)
                            else
                                painterResource(R.drawable.icon_unchecked_checkbox),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onFilterClick(FilterType.SHOE_SIZE, item.id)
                            }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "(${item.itemStock})",
                        color = buttonBorderColor,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

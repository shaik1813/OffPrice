package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.utils.Constant.COLOR_FILTER
import com.apparel.offprice.features.plp.data.model.FilterGroup
import com.apparel.offprice.features.plp.data.model.FilterItem
import com.apparel.offprice.features.plp.data.model.FilterType

@Composable
fun InlineFilterRow(
    filters: List<FilterGroup>,
    onFilterItemClick: (FilterType, FilterItem) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) } // Note: use remember for local reference, no need to add in UiState

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = "Filters for you",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 14.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        InlineFilterTabs (
            filterTabs = filters,
            selectedIndex = selectedIndex,
            onTabSelected = { index, filterGroup ->
                selectedIndex = index
            }
        )

        Spacer(Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val filterType = filters[selectedIndex].type
            when (filterType) {
                FilterType.COLOR -> {
                    val size = filters[selectedIndex].items.size
                    if (size > COLOR_FILTER) {
                        filters[selectedIndex].items.take(COLOR_FILTER).forEach { filter ->
                            ColorChip(filter) {
                                onFilterItemClick(filterType, filter)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+${size - COLOR_FILTER}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }

                    } else {
                        filters[selectedIndex].items.forEach { filter ->
                            ColorChip(filter) {
                                onFilterItemClick(filterType, filter)
                            }
                        }
                    }

                }

                FilterType.CLOTHING_SIZE -> {
                    filters[selectedIndex].items.forEach { filter ->
                        ClothingSizeChip(filter) {
                            onFilterItemClick(filterType, filter)
                        }
                    }
                }

                FilterType.PRICE -> {}
                else -> {}
            }
        }
    }
}
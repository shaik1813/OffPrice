package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.features.plp.data.model.FilterGroup

@Composable
fun InlineFilterTabs(
    filterTabs: List<FilterGroup>,
    selectedIndex: Int,
    onTabSelected: (Int, FilterGroup) -> Unit
) {
    Column {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            itemsIndexed(filterTabs) { index, category ->
                Column(
                    modifier = Modifier
                        .clickable { onTabSelected(index, category) }
                ) {
                    Text(
                        text = category.title.uppercase(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedIndex == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            buttonBorderColor
                        }
                    )

                    Spacer(Modifier.height(6.dp))

                    if (selectedIndex == index) {
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .widthIn(min = 24.dp, max = 48.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = borderColor
        )
    }
}
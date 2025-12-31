package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.AppBasicSearchField
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.utils.toComposeColorSafe
import com.apparel.offprice.features.plp.data.model.FilterGroup
import com.apparel.offprice.features.plp.data.model.FilterType

@Composable
fun CategoryFilterListItem(
    filter: List<FilterGroup>,
    onFilterClick: (FilterType, String) -> Unit
) {
    val listItem = filter.first().items
    val filterType = filter.first().type
    val filterName = filter.first().title
    var searchItem by remember { mutableStateOf("") } // Note: use remember for local reference, no need to add in UiState

    when (filterType) {
        FilterType.BRAND, FilterType.CATEGORY -> {
            val filterList = listItem.filter { it.name.contains(searchItem, ignoreCase = true) }
            AppBasicSearchField(
                value = searchItem,
                enabled = true,
                onValueChange = { searchItem = it },
                placeholder = "Search $filterName",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
            )
            LazyColumn {
                items(filterList) { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 8.dp)
                    ) {
                        Image(
                            painter =
                                if (item.isSelected) painterResource(R.drawable.icon_checked_checkbox) else painterResource(
                                    R.drawable.icon_unchecked_checkbox
                                ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    onFilterClick(filterType, item.id)
                                }

                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "(${item.itemStock})",
                            color = buttonBorderColor,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp
                            )
                        )
                    }
                }
            }
        }

        else -> {
            if (listItem.isNotEmpty()) {
                LazyColumn {
                    items(listItem) { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp, horizontal = 8.dp)
                        ) {
                            Image(
                                painter =
                                    if (item.isSelected) painterResource(R.drawable.icon_checked_checkbox) else painterResource(
                                        R.drawable.icon_unchecked_checkbox
                                    ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        onFilterClick(filterType, item.id)
                                    }

                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            if (filterType == FilterType.COLOR) {
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .clip(RectangleShape)
                                        .background(
                                            color = item.hexColor?.toComposeColorSafe()
                                                ?: Color.Black,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = borderColor,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                ) {}
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "(${item.itemStock})",
                                color = buttonBorderColor,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.label_no_item_found),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
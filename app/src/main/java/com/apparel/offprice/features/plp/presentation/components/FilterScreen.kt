package com.apparel.offprice.features.plp.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.AppBasicSearchField
import com.apparel.offprice.common.component.BottomDoubleActionButton
import com.apparel.offprice.common.theme.IconBackgroundColor
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.utils.toComposeColorSafe
import com.apparel.offprice.features.plp.data.model.FilterGroup
import com.apparel.offprice.features.plp.data.model.FilterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    filterList: List<FilterGroup>,
    selectedFilter: FilterType,
    onFilterClick: (FilterType) -> Unit,
    onFilterItemClick: (FilterType, String) -> Unit,
    onClose: () -> Unit,
    onApply: () -> Unit,
    onClearAll: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.filter_label).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = "Back",
                        modifier = Modifier.clickable { onClose() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.shadow(
                    elevation = 6.dp,
                    spotColor = Color.Black
                )
            )
        },
        bottomBar = {
            BottomDoubleActionButton(
                leftButtonText = stringResource(R.string.label_clear_all).uppercase(),
                rightButtonText = stringResource(R.string.label_view_product).uppercase(),
                onLeftClick = {
                    onClearAll()
                },
                onRightClick = {
                    onApply()
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f) // body takes all remaining height
                    .fillMaxWidth()
            ) {

                // LEFT FILTER MENU
                LazyColumn(
                    modifier = Modifier
                        .width(150.dp)
                        .background(IconBackgroundColor)
                        .padding(vertical = 8.dp)
                ) {
                    items(filterList) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                                .clickable { onFilterClick(item.type) }
                                .background(if (selectedFilter == item.type) Color.Black else Color.Transparent)
                                .padding(vertical = 10.dp, horizontal = 8.dp)
                        ) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (selectedFilter == item.type) Color.White else buttonBorderColor,
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }

                // RIGHT CONTENT
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    when (selectedFilter) {
                        FilterType.BRAND -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.BRAND },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.CATEGORY -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.CATEGORY },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.CLOTHING_SIZE -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.CLOTHING_SIZE },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.COLOR -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.COLOR },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.DISCOUNT -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.DISCOUNT },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.DELIVERY_TYPE -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.DELIVERY_TYPE },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.OCCASION -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.OCCASION },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        FilterType.BY_STOCK -> {
                            CategoryFilterListItem(
                                filter = filterList.filter { it.type == FilterType.BY_STOCK },
                                onFilterClick = { filterType, itemId ->
                                    onFilterItemClick(filterType, itemId)
                                }
                            )
                        }

                        else -> Text(
                            text = "No UI implemented for '${selectedFilter}' yet",
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

        }
    }
}
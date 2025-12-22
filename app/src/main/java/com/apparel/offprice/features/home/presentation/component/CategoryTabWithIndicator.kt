package com.apparel.offprice.features.home.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.features.home.data.model.LOneCategoryItem

@Composable
fun CategoryTabsWithIndicator(
    categories: List<LOneCategoryItem>,
    isHome: Boolean = false,
    onCategorySelected: (LOneCategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var indicatorOffset by remember { mutableStateOf(0.dp) }
    var indicatorWidth by remember { mutableStateOf(0.dp) }

    val animatedOffset by animateDpAsState(indicatorOffset)
    val animatedWidth by animateDpAsState(indicatorWidth)

    val density = LocalDensity.current
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryTabItem(
                    categoryItem = category,
                    isHome = isHome,
                    onClick = { onCategorySelected(category) },
                    onTextPositioned = { textCoordinates ->
                        val parentCoordinates = textCoordinates.parentLayoutCoordinates
                            ?: return@CategoryTabItem

                        with(density) {
                            indicatorWidth = parentCoordinates.size.width.toDp()

                            indicatorOffset =
                                (parentCoordinates.positionInParent().x).toDp()
                        }
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .height(1.dp)
                .background(if (isHome) Color(0x14FFFFFF) else Color(0xFFE5E5E5))
                .align(Alignment.BottomStart)
        )
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .padding(top = 36.dp)
                .width(animatedWidth)
                .height(2.dp)
                .background(Color.Red)
                .align(Alignment.BottomStart)
        )

    }
}

@Composable
fun CategoryTabItem(
    categoryItem: LOneCategoryItem,
    isHome: Boolean,
    onClick: () -> Unit,
    onTextPositioned: (LayoutCoordinates) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = categoryItem.title.uppercase(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 14.sp
            ),
            color = if (categoryItem.isSelected) Color(0xFFED1D2C) else {
                if (isHome) {
                    Color.White
                } else {
                    Color(0xFF040707)
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.onGloballyPositioned { coordinates ->
                if (categoryItem.isSelected) {
                    onTextPositioned(coordinates)
                }
            }
        )
    }
}
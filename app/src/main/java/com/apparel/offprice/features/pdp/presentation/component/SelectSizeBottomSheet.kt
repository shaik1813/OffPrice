package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.features.pdp.data.model.SizeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSizeBottomSheet(
    screenHeight: Dp,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onAddToBag: () -> Unit
) {


    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        dragHandle = null
    ) {

        Box(
            modifier = Modifier
                .background(Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 16.dp)
            ) {

                HeaderSection(onDismiss)

                Spacer(Modifier.height(24.dp))

                val sizeList = listOf(
                    SizeItem(id = "1","S", 4),
                    SizeItem(id = "2","M", 1),
                    SizeItem(id = "3","L", 2),
                    SizeItem(id = "4","XL", 0),  // Sold out example
                    SizeItem(id = "5","XXL", 5),
                    SizeItem(id = "6","XXXL", 0, disabled = true )
                )

                var selectedItem by remember { mutableStateOf(0) }


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 3.8f)
                ) {
                    /*List items*/
                    val state = rememberLazyListState()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 4f),  // 👈 allows scroll under fixed button
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        state = state
                    ) {
                        itemsIndexed(sizeList) { index, size ->
                            SizeRow(size, index, selectedItem) {
                                selectedItem = it
                            }
                        }
                    }

                    // ------- Custom Scroll Bar --------
                    val totalItems = sizeList.size
                    val visibleItems = state.layoutInfo.visibleItemsInfo.size

                    if (visibleItems < totalItems) {

                        val scrollProgress = if (state.firstVisibleItemIndex == 0) {
                            0f
                        } else {
                            state.firstVisibleItemIndex.toFloat() / (totalItems - visibleItems).toFloat()
                        }

                        val thumbHeightFraction = visibleItems.toFloat() / totalItems.toFloat()

                        val thumbOffset = scrollProgress * (1f - thumbHeightFraction)

                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .width(4.dp)
                                .height(screenHeight / 4f)
                                .padding(vertical = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(screenHeight / 4f)
                                    .width(2.dp)
                                    .align(Alignment.TopCenter)
                                    .background(Color(0xFFEAEAEA)) // full scrollbar background
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxHeight(thumbHeightFraction)
                                    .width(4.dp)
                                    .offset(y = thumbOffset * (screenHeight.value / 4f).dp)
                                    .align(Alignment.TopCenter)
                                    .background(Color.Black.copy(alpha = 0.5f)) // Scroll thumb
                            )
                        }
                    }
                    /* ScrollBar Section*/

                }

                Spacer(modifier = Modifier.size(45.dp))
            }


            AddToBagSection(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onAddToBag = { onAddToBag() }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToBagSection(modifier: Modifier, onAddToBag: () -> Unit) {


    Column(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .height(46.dp)
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = loginButtonColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("-", fontSize = 18.sp)
                    Text("1", fontSize = 14.sp, style = MaterialTheme.typography.bodyMedium)
                    Text("+", fontSize = 18.sp)
                }
            }

            // Add to Bag Button
            Box(
                modifier = Modifier
                    .height(46.dp)
                    .weight(2f)
                    .background(loginButtonColor, RoundedCornerShape(6.dp))
                    .clickable {
                        onAddToBag()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.add_to_bag),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 12.sp
                )
            }

        }
    }

}

@Composable
fun HeaderSection(onDismiss: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.select_size),
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleMedium,
            color = loginButtonColor
        )

        Row() {
            SizeTypeTabs()
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onDismiss() }
                    .padding(start = 5.dp)
            )
        }
    }
}


@Composable
fun SizeTypeTabs() {
    var selected by remember { mutableStateOf(0) }
    val tabs = listOf("EU", "UK", "US")

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, text ->
            Column() {
                Text(
                    text,
                    style = if (selected == index) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleMedium,
                    color = if (selected == index) loginButtonColor else Color(0xFFACACAC),
                    modifier = Modifier.clickable { selected = index }
                )
                Box(
                    modifier = Modifier
                        .height(1.5.dp)
                        .width(19.dp)
                        .background(
                            if (selected == index) Color(0xFFB5373D) else Color.Transparent
                        )
                )
            }

        }
    }
}


@Composable
fun SizeRow(size: SizeItem, index: Int, selectedItem: Int, onSelect: (Int) -> Unit) {
    val borderColor = if (selectedItem == index) Color(0xFFD48C90) else Color.Transparent
    val textColor = if (size.disabled) Color.Black else Color.Gray

    Row(
        modifier = Modifier
            .padding(end = 10.dp)
            .fillMaxWidth()
            .background(if (selectedItem == index) Color(0xFFF6E6E7).copy(alpha = 0.5f) else Color.Transparent)
            .border(
                if (selectedItem == index) 1.dp else 0.dp,
                borderColor,
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onSelect(index) },

        ) {

        Text(
            size.label,
            style = MaterialTheme.typography.titleMedium,
            color = textColor, fontSize = 14.sp,
            modifier = Modifier.weight(0.3f)
        )

        if (size.stock == 0) {
            size.stock?.let {
                Text(
                    stringResource(com.apparel.offprice.R.string.out_of_stock),
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFFC1585D),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(0.7f)
                        .align(alignment = Alignment.CenterVertically)

                )
            }
        }
    }
}


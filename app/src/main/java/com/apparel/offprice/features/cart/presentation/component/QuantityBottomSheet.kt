package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantityBottomSheet(
    screenHeight: Dp, onSelectItem: (item: Int) -> Unit, onDismiss: () -> Unit, onSumbit: () -> Unit
) {

    val quantities: List<Int> = listOf(1, 2, 3, 4, 5, 6)
    var selectedQuantity by remember { mutableStateOf(0) }

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(),
        onDismissRequest = { onDismiss() },
        dragHandle = null
    ) {

        Box(modifier = Modifier.background(Color.White)) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)

            ) {
                /*Header*/
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.select_quantity),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 16.sp
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color(0xFF677089),
                        modifier = Modifier.clickable {
                            onDismiss()
                        })
                }

                Spacer(modifier = Modifier.height(12.dp))

                /*Quantity list*/
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 3.8f)
                ) {
                    val state = rememberLazyListState()

                    LazyColumn(
                        state = state,
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        items(quantities) { qty ->
                            QuantityRow(
                                value = qty, selectedQuantity, onClick = {
                                    onSelectItem(it)
                                    selectedQuantity = it
                                }
                            )
                        }
                    }


                    // ------- Custom Scroll Bar --------
                    val totalItems = quantities.size
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

                }

                Spacer(modifier = Modifier.height(50.dp))


            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .shadow(1.dp) // 👈 Only top shadow
                        .background(Color.Transparent)
                )


                Spacer(modifier = Modifier.height(10.dp))

                /*Submit Button*/
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(loginButtonColor, RoundedCornerShape(6.dp))
                        .clickable {
                            onSumbit()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.submit),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 12.sp
                    )
                }

            }

        }

    }
}

package com.apparel.offprice.features.wishlist.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.offerCardColor
import com.apparel.offprice.features.pdp.data.model.SizeItem
import com.apparel.offprice.features.wishlist.presentation.screen.WishListContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListSizeSelectionBottomSheet(
    state: (WishListContract.UiState),
    onEvent: (WishListContract.UiEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.select_size).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .clickable {
                                onDismiss()
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val sheetState = rememberLazyListState()
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(400.dp)
                            .padding(horizontal = 16.dp),
                        state = sheetState
                    ) {
                        itemsIndexed(
                            items = state.sizeListItem,
                            key = { index, size -> size.id }) { index, size ->
                            SizeRow(
                                size = size,
                                isSelected = size.id == state.selectedSizeId,
                                onClick = {
                                    onEvent.invoke(WishListContract.UiEvent.SelectSize(size.id))
                                }
                            )
                            if (index < state.sizeListItem.lastIndex) {
                                HorizontalDivider(color = borderColor)
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                FooterActions(
                    quantity = state.quantity,
                    onIncrease = { onEvent(WishListContract.UiEvent.IncreaseQty) },
                    onDecrease = { onEvent(WishListContract.UiEvent.DecreaseQty) },
                    onAddToBag = { onEvent(WishListContract.UiEvent.MoveToBag) }
                )
            }
        }
    }
}

@Composable
fun SizeRow(
    size: SizeItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val isOutOfStock = size.stock == 0
    val showLowStock = size.stock in 1..5

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(end = 10.dp)
            .background(
                color = if (isSelected) Color(0x80F6E6E7) else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        1.dp,
                        Color(0xFFD48C90),
                        MaterialTheme.shapes.medium
                    )
                } else Modifier
            )
            .clickable(
                enabled = !isOutOfStock,
                onClick = onClick
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = size.label,
            style = MaterialTheme.typography.titleMedium,
            color = if (isOutOfStock) Color.LightGray else Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        when {
            isOutOfStock -> {
                Text(
                    text = stringResource(R.string.out_of_stock),
                    color = offerCardColor,
                    style = MaterialTheme.typography.displaySmall
                )
            }

            showLowStock -> {
                Text(
                    text = "${size.stock} LEFT",
                    color = nonreturnTxtColor,
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}


@Composable
fun FooterActions(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onAddToBag: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 14.dp,
                spotColor = Color(0x17000000)
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "-",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp).clickable { onDecrease() }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = " $quantity ",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "+",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp).clickable { onIncrease() })
            }
            Spacer(Modifier.width(12.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = onAddToBag,
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(R.string.label_move_to_bag).uppercase(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 12.sp
                    ),
                    color = Color.White
                )
            }
        }
    }
}


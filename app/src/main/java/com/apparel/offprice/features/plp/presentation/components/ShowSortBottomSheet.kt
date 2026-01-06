package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.features.plp.data.model.SortProductItem
import com.apparel.offprice.features.plp.presentation.screens.bestPrice.BestPriceContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSortBottomSheet(
    sortList: List<SortProductItem>,
    onClicked: (SortProductItem) -> Unit,
    onDismiss: () -> Unit
) {
    val sortSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sortSheetState,
        onDismissRequest = { onDismiss() },
        dragHandle = null,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        sheetMaxWidth = BottomSheetDefaults.SheetPeekHeight,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.label_sort),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { onDismiss() }) {
                    Icon(
                        painter = painterResource(R.drawable.close_24px),
                        contentDescription = "Close",
                        tint = Color.Black
                    )
                }
            }
            HorizontalDivider(color = borderColor, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                itemsIndexed(sortList, key = { index, item -> item.id }) { index, item ->
                    SortOptionItem(
                        title = item.title,
                        selected = item.isSelected,
                        showDivider = index != sortList.lastIndex
                    ) {
                        onClicked(item)
                        onDismiss()
                    }
                }
            }
        }
    }
}

@Composable
fun SortOptionItem(
    title: String,
    selected: Boolean,
    showDivider: Boolean = true,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = inputTextColor,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color.Black
                )
            }
        }

        if (showDivider) {
            HorizontalDivider(color = borderColor)
        }
    }
}
package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.apparel.offprice.features.plp.presentation.screens.bestPrice.BestPriceContract


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowFilterBottomSheet(
    state: BestPriceContract.UiState,
    onEvent: (BestPriceContract.UiEvent) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        dragHandle = null,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .statusBarsPadding(),
        sheetMaxWidth = BottomSheetDefaults.SheetPeekHeight,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        FilterScreen(
            filterList = state.filters,
            selectedFilter = state.selectedFilterType,
            onFilterClick = { filterType ->
                onEvent.invoke(BestPriceContract.UiEvent.OnFilterTypeClicked(filterType))
            },
            onFilterItemClick = { filterType, itemId ->
                onEvent.invoke(BestPriceContract.UiEvent.OnFilterItemClicked(filterType, itemId))
            },
            onClose = {
                onEvent.invoke(BestPriceContract.UiEvent.FilterSheetClosed)
            },
            onApply = {
                onEvent.invoke(BestPriceContract.UiEvent.FilterSheetClosed)
            },
            onClearAll = {
                onEvent.invoke(BestPriceContract.UiEvent.OnClearAllClicked)
            }
        )
    }
}
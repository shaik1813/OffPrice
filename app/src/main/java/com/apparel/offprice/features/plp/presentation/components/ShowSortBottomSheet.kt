package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.apparel.offprice.features.plp.presentation.screens.SortBottomSheet
import com.apparel.offprice.features.plp.presentation.screens.bestPrice.BestPriceContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSortBottomSheet(
    onEvent: (BestPriceContract.UiEvent) -> Unit,
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
    ) {
        SortBottomSheet(
            onClose = {
                onEvent.invoke(BestPriceContract.UiEvent.ToggleSortSheet)
            },
            onSortSelected = { sortType ->
                onEvent.invoke(BestPriceContract.UiEvent.ToggleSortSheet)
            }
        )
    }

}
package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.apparel.offprice.features.plp.presentation.screens.FilterScreen
import com.apparel.offprice.features.plp.presentation.screens.bestPrice.BestPriceContract


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowFilterBottomSheet(
    onEvent: (BestPriceContract.UiEvent) -> Unit,
    onDismiss: () -> Unit
){
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        dragHandle = null,
        modifier = Modifier.fillMaxWidth()
            .navigationBarsPadding(),
        sheetMaxWidth = BottomSheetDefaults.SheetPeekHeight,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            FilterScreen(
                onClose = {
                    onEvent.invoke(BestPriceContract.UiEvent.ToggleFilterSheet)
                },
                onApply = {
                    onEvent.invoke(BestPriceContract.UiEvent.ToggleFilterSheet)
                },
                onClearAll = {

                }
            )
        }
    }
}
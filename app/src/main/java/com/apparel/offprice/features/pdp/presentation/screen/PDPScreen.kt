package com.apparel.offprice.features.pdp.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.pdp.presentation.component.AddToBasketBottomSheet
import com.apparel.offprice.features.pdp.presentation.component.ElevatedLine
import com.apparel.offprice.features.pdp.presentation.component.PDPBottomView
import com.apparel.offprice.features.pdp.presentation.component.ProductDescSection
import com.apparel.offprice.features.pdp.presentation.component.ProductImageSection
import com.apparel.offprice.features.pdp.presentation.component.SelectSizeBottomSheet
import com.apparel.offprice.features.pdp.presentation.component.ShareProductBottomSheet


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PDPscreen(viewModel: PDPViewModel = hiltViewModel()) {

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    var (state, event, effect) = use(viewModel = viewModel)

    if (state.isAddBasketSheet) {

        AddToBasketBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onContinue = {
                event(PDPContract.UiEvent.onCloseAddToBagSheet)
            },
            onDismiss = {
                event(PDPContract.UiEvent.onCloseAddToBagSheet)
            },
            onGoToBag = {})
    }

    if (state.isSizeSelectSheet) {
        SelectSizeBottomSheet(
            screenHeightDp,
            sheetState = rememberModalBottomSheetState(),
            onDismiss = { event(PDPContract.UiEvent.onCloseSizeSelectSheet) },
            onAddToBag = {
                viewModel.event(PDPContract.UiEvent.onOpenAddToBagSheet)
            }
        )
    }

    if (state.isShareProductSheet) {
        ShareProductBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onDismiss = { event(PDPContract.UiEvent.onCloseShareProductSheet) }
        )
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(modifier = Modifier.systemBarsPadding()) {

            item { ProductImageSection(onShareClick = {event(PDPContract.UiEvent.onOpenShareProductSheet)}) }

            item { ProductDescSection() }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {

            ElevatedLine()

            PDPBottomView(onSizeSelect = { viewModel.event(PDPContract.UiEvent.onOpenSizeSelectSheet)},
                onAddToBag = { viewModel.event(PDPContract.UiEvent.onOpenAddToBagSheet) })

        }

    }


}
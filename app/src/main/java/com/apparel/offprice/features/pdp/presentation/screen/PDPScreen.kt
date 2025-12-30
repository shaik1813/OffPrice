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
import com.apparel.offprice.features.pdp.presentation.component.SimilarPLPSheet
import com.apparel.offprice.features.pdp.presentation.component.SizeGuideScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PDPScreen(
    productId: String?,
    viewModel: PDPViewModel = hiltViewModel()) {

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    val (state, event, effect) = use(viewModel = viewModel)

    Log.e("TAG", "PDPScreen: $productId" )

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

    if(state.isSimilarPLPSheet){
        SimilarPLPSheet(
            sheetState = rememberModalBottomSheetState(),
            onDismiss = { event(PDPContract.UiEvent.onCloseSimilarProductSheet) })
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        if (!state.isSizeGuideSheet) {
            LazyColumn(modifier = Modifier.systemBarsPadding()) {

                item {
                    ProductImageSection(
                        onShareClick = { event(PDPContract.UiEvent.onOpenShareProductSheet) },
                        onClickSimilar = {
                            event(PDPContract.UiEvent.onOpenSimilarProductSheet)
                        })
                }

                item {
                    ProductDescSection(onSizeGuideClick = {
                        event(PDPContract.UiEvent.onOpenSizeGuideSheet)
                    })
                }
            }
        }

        SizeGuideScreen(
            isVisible = state.isSizeGuideSheet,
            onDismiss = { event(PDPContract.UiEvent.onCloseSizeGuideSheet) }
        )

        if (!state.isSizeGuideSheet) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {

                ElevatedLine()

                PDPBottomView(
                    onSizeSelect = { viewModel.event(PDPContract.UiEvent.onOpenSizeSelectSheet) },
                    onAddToBag = { viewModel.event(PDPContract.UiEvent.onOpenAddToBagSheet) })

            }
        }


    }


}
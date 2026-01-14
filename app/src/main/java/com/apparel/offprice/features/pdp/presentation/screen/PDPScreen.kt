package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.pdp.data.model.tabbyPaymentDetail
import com.apparel.offprice.features.pdp.data.model.tamaraPaymentDetail
import com.apparel.offprice.features.pdp.presentation.component.AddToBasketBottomSheet
import com.apparel.offprice.features.pdp.presentation.component.ElevatedLine
import com.apparel.offprice.features.pdp.presentation.component.LocationSheetPDP
import com.apparel.offprice.features.pdp.presentation.component.PDPBottomView
import com.apparel.offprice.features.pdp.presentation.component.ProductDescSection
import com.apparel.offprice.features.pdp.presentation.component.ProductImageSection
import com.apparel.offprice.features.pdp.presentation.component.SelectSizeBottomSheet
import com.apparel.offprice.features.pdp.presentation.component.ShareProductBottomSheet
import com.apparel.offprice.features.pdp.presentation.component.SimilarPLPSheet
import com.apparel.offprice.features.pdp.presentation.component.SizeGuideScreen
import com.apparel.offprice.features.pdp.presentation.component.TabbyDetailSheet
import com.apparel.offprice.features.pdp.presentation.component.TamaraDetailSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PDPScreen(
    productId: String?, viewModel: PDPViewModel = hiltViewModel()
) {

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    val (state, event, effect) = use(viewModel = viewModel)


    if (state.isAddBasketSheet) {
        AddToBasketBottomSheet(sheetState = rememberModalBottomSheetState(), onContinue = {
            event(PDPContract.UiEvent.onCloseAddToBagSheet)
        }, onDismiss = {
            event(PDPContract.UiEvent.onCloseAddToBagSheet)
        }, onGoToBag = {})
    }

    if (state.isSizeSelectSheet) {
        SelectSizeBottomSheet(
            screenHeightDp,
            sheetState = rememberModalBottomSheetState(),
            onDismiss = { event(PDPContract.UiEvent.onCloseSizeSelectSheet) },
            onAddToBag = {
                viewModel.event(PDPContract.UiEvent.onOpenAddToBagSheet)
            })
    }

    if (state.isOpenLocation) LocationSheetPDP(
        state.selectedCity,
        onCitySelected = {
            event(PDPContract.UiEvent.onCitySelect(it))
        },
        onDismiss = { event(PDPContract.UiEvent.onCloseBottomSheetLocation) }
    )


    if (state.isShareProductSheet) {
        ShareProductBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onDismiss = { event(PDPContract.UiEvent.onCloseShareProductSheet) })
    }

    if (state.isSimilarPLPSheet) {
        SimilarPLPSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismiss = { event(PDPContract.UiEvent.onCloseSimilarProductSheet) },
            onWishlistClick = {},
            onProductClick = {})
    }

    if (state.tabbySheet) {
        TabbyDetailSheet (
            sheetState = rememberModalBottomSheetState(),
            paymentInfo = tabbyPaymentDetail,
            onDismiss = { event(PDPContract.UiEvent.onCloseTabbySheet) })
    }

    if (state.tamaraSheet) {
        TamaraDetailSheet (
            sheetState = rememberModalBottomSheetState(),
            paymentInfo = tamaraPaymentDetail,
            onDismiss = { event(PDPContract.UiEvent.onCloseTamaraSheet) })
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {

        if (!state.isSizeGuideSheet) {
            LazyColumn {
                item {
                    Box() {
                        ProductImageSection(
                            state.pdpDetail!!,
                            modifier = Modifier.align(Alignment.TopCenter),
                            onShareClick = { event(PDPContract.UiEvent.onOpenShareProductSheet) },
                            onClickSimilar = {
                                event(PDPContract.UiEvent.onOpenSimilarProductSheet)
                            },
                            onWishListClick = {
                                event(PDPContract.UiEvent.onWishListClicked(it))
                            })

                        val configuration = LocalConfiguration.current

                        val screenHeight = remember(configuration) {
                            configuration.screenHeightDp.dp / 2.06f
                        }

                        ProductDescSection(
                            state.pdpDetail,
                            state = state,event = event,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = screenHeight),
                            onOpenLocationSheet = {
                                event(PDPContract.UiEvent.onOpenBottomSheetLocation)
                            },
                            onTabbyInfoClick = {
                                event(PDPContract.UiEvent.onOpenTabbySheet)
                            },
                            onTamaraInfoClick = {
                                event(PDPContract.UiEvent.onOpenTamaraSheet)
                            },
                            onSizeGuideClick = {
                                event(PDPContract.UiEvent.onOpenSizeGuideSheet)
                            })
                    }
                }

            }
        }

        SizeGuideScreen(
            isVisible = state.isSizeGuideSheet,
            onDismiss = { event(PDPContract.UiEvent.onCloseSizeGuideSheet) })

        if (!state.isSizeGuideSheet) {
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {

                ElevatedLine()

                PDPBottomView(
                    onSizeSelect = { viewModel.event(PDPContract.UiEvent.onOpenSizeSelectSheet) },
                    onAddToBag = { viewModel.event(PDPContract.UiEvent.onOpenAddToBagSheet) })

            }
        }
    }

}
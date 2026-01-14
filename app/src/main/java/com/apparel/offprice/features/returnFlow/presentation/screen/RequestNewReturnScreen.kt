package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.checkout.presentation.components.TopBar
import com.apparel.offprice.features.returnFlow.presentation.component.ContinueReturnButton
import com.apparel.offprice.features.returnFlow.presentation.component.RequestReturnItemCard
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnReasonBottomSheet

@Composable
fun RequestNewReturnScreen(
    viewModel: RequestNewReturnViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onContinue: () -> Unit
) {

    val (state, event, effect) = use(viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            RequestNewReturnContract.UiEffect.NavigateBack -> onBackClick()
            RequestNewReturnContract.UiEffect.NavigateToNextStep -> onContinue()
            is RequestNewReturnContract.UiEffect.ShowError -> {}
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopBar(
                title = stringResource(R.string.return_order_label).uppercase(),
                onBack = {
                    event(RequestNewReturnContract.UiEvent.OnBackClick)
                }
            )
        },
        bottomBar = {
            ContinueReturnButton(
                enabled = state.selectedItemIds.isNotEmpty(),
                onClick = {
                    event(RequestNewReturnContract.UiEvent.OnContinueClick)
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            items(state.items) { item ->
                RequestReturnItemCard(
                    item = item,
                    selected = state.selectedItemIds.contains(item.returnId),
                    selectedReason = state.selectedReason,
                    onSelect = {
                        event(RequestNewReturnContract.UiEvent.OnItemSelected(item.returnId))
                    },
                    onReasonClick = {
                        event(RequestNewReturnContract.UiEvent.OnReasonClick)
                    }
                )

            }
        }
    }

    if (state.isReasonSheetOpen) {
        ReturnReasonBottomSheet(
            onSelect = {
                event(RequestNewReturnContract.UiEvent.OnReasonSelected(it))
            },
            onDismiss = {
                event(RequestNewReturnContract.UiEvent.OnCloseReasonSheet)
            }
        )
    }
}

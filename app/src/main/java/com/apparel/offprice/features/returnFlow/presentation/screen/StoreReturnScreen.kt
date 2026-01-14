package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.checkout.presentation.components.TopBar
import com.apparel.offprice.features.returnFlow.presentation.component.PickupStoreCard
import com.apparel.offprice.features.returnFlow.presentation.component.StoreReturnBottomBar

@Composable
fun StoreReturnScreen(
    viewModel: StoreReturnViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onContinue: () -> Unit
) {

    val (state, event, effect) = use(viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            StoreReturnContract.UiEffect.NavigateBack -> onBackClick()
            StoreReturnContract.UiEffect.NavigateNext -> onContinue()
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopBar(
                title = stringResource(R.string.store_return).uppercase(),
                onBack = {
                    event(StoreReturnContract.UiEvent.OnCancelClick)
                }
            )
        },
        bottomBar = {
            StoreReturnBottomBar(
                enabled = state.selectedStoreId != null,
                onCancel = {
                    event(StoreReturnContract.UiEvent.OnCancelClick)
                },
                onContinue = {
                    event(StoreReturnContract.UiEvent.OnContinueClick)
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(state.stores) { store ->
                PickupStoreCard(
                    store = store,
                    selected = state.selectedStoreId == store.id,
                    onClick = {
                        event(
                            StoreReturnContract.UiEvent.OnStoreSelected(
                                store.id
                            )
                        )
                    }
                )
            }
        }
    }
}

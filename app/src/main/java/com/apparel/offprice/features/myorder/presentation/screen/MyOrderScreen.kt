package com.apparel.offprice.features.myorder.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.myorder.presentation.component.MyOrderheader
import com.apparel.offprice.features.myorder.presentation.component.OrderEmptyScreen
import com.apparel.offprice.features.myorder.presentation.component.OrderCard
import com.apparel.offprice.features.myorder.presentation.component.OrderFilterBottomSheet
import com.apparel.offprice.features.myorder.data.OrderFilter


@Composable
fun MyOrderScreen(
    onNavigateBack: () -> Unit,
    viewModel: MyOrderViewModel = hiltViewModel()
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            MyOrderContract.UiEffect.OnBackPressed -> {
                onNavigateBack()
            }
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_my_orders).uppercase()) {
                event.invoke(MyOrderContract.UiEvent.OnBackPressed)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            MyOrderheader(state.selectedFilter,
                onAllOrdersClicked = {
                    event.invoke(MyOrderContract.UiEvent.OnAllOrdersClicked)
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (state.orderData.isEmpty()) {
                OrderEmptyScreen(
                    onViewAllOrdersClick = {
                        event.invoke(MyOrderContract.UiEvent.OnFilterSelected(OrderFilter.ALL))
                    }
                )
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    items(state.orderData) { order ->
                        OrderCard(screenWidth, order)
                    }
                }
            }
        }
    }

    if (state.isFilterBottomSheetOpen) {
        OrderFilterBottomSheet(
            selectedFilter = state.selectedFilter,
            onFilterSelected = { filter ->
                event.invoke(MyOrderContract.UiEvent.OnFilterSelected(filter))
            },
            onDismiss = {
                event.invoke(MyOrderContract.UiEvent.OnDismissBottomSheet)
            }
        )
    }
}
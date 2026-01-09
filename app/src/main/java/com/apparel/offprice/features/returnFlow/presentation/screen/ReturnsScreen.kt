package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.features.profile.presentation.screen.userprofile.UserProfileContract
import com.apparel.offprice.features.returnFlow.presentation.component.RequestReturnButton
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnFilterBottomSheet
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnItemCard
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnsHeaderRow

@Composable
fun ReturnsScreen(
    viewModel: ReturnsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToReturnDetails: (String) -> Unit,
    onNavigateToRequestReturn: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val event = viewModel::event

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ReturnsContract.UiEffect.NavigateBack ->
                    onNavigateBack()

                ReturnsContract.UiEffect.NavigateToRequestReturn ->
                    onNavigateToRequestReturn()

                is ReturnsContract.UiEffect.NavigateToReturnDetails ->
                    onNavigateToReturnDetails(effect.returnId)

                is ReturnsContract.UiEffect.ShowError -> {
                    // show snackbar
                }
            }
        }
    }

    Scaffold(modifier = Modifier.statusBarsPadding(),
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_returns).uppercase()) {
                event.invoke(ReturnsContract.UiEvent.OnBackClick)
            }
        },
        bottomBar = {
            RequestReturnButton {
                event(ReturnsContract.UiEvent.OnRequestNewReturnClick)
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            //COMPLETED + FILTER ROW
            ReturnsHeaderRow(
                selectedFilter = state.selectedFilter,
                onFilterClick = {
                    event(ReturnsContract.UiEvent.OnFilterClick)
                }
            )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.returnsList) { item ->
                ReturnItemCard(
                    item = item,
                    onClick = {
                        event(
                            ReturnsContract.UiEvent.OnReturnItemClick(
                                item.returnId
                            )
                        )
                    }
                )
            }
        }
    }
    }

    if (state.isFilterBottomSheetOpen) {
        ReturnFilterBottomSheet(
            selectedFilter = state.selectedFilter,
            onFilterSelected = {
                event(ReturnsContract.UiEvent.OnFilterSelected(it))
            },
            onSubmit = {
                event(ReturnsContract.UiEvent.OnFilterSubmit)
            }
        )
    }
}

package com.apparel.offprice.features.returnFlow.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.returnFlow.presentation.component.PickupAddressCard
import com.apparel.offprice.features.returnFlow.presentation.component.RefundProcessedCard
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnIdStatusSection
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnedProductCard
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReturnDetailsScreen(
    returnId: String,
    onBackClick: () -> Unit,
    viewModel: ReturnDetailsViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel)

    // 🔹 LOAD DATA ON ENTRY
    LaunchedEffect(returnId) {
        viewModel.loadReturnDetails(returnId)
    }

    // 🔹 EFFECTS (Navigation, Snackbar, etc.)
    effect.CollectInLaunchedEffect {
        when (it) {
            ReturnDetailsContract.UiEffect.NavigateBack -> {
                onBackClick()
            }

            is ReturnDetailsContract.UiEffect.ShowError -> {
                // show snackbar
            }
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(R.string.label_returns).uppercase(),
                onBackPressed = {
                    event(ReturnDetailsContract.UiEvent.OnBackClick)
                }
            )
        }
    ) { padding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.returnItem != null -> {
                val item = state.returnItem

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    ReturnIdStatusSection(
                        returnId = item.returnId,
                        requestedOn = LocalDate.now(),
                        status = item.status
                    )

                    RefundProcessedCard(item = item)

                    Spacer(Modifier.height(16.dp))

                    PickupAddressCard()

                    Spacer(Modifier.height(12.dp))

                    ReturnedProductCard(
                        product = item
                    )
                }
            }
        }
    }
}


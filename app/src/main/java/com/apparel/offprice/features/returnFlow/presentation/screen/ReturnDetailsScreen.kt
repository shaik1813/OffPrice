package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.features.returnFlow.presentation.component.PickupAddressCard
import com.apparel.offprice.features.returnFlow.presentation.component.RefundProcessedCard
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnIdStatusSection
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnedProductCard

@Composable
fun ReturnDetailsScreen(
    returnId: String,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(R.string.label_returns).uppercase(),
                onBackPressed = onBackClick
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ReturnIdStatusSection(returnId)

            RefundProcessedCard()

            PickupAddressCard()

            ReturnedProductCard()
        }
    }
}

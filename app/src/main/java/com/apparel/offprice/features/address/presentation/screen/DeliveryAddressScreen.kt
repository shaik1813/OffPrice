package com.apparel.offprice.features.address.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.BottomSingleActionButton
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.address.presentation.component.DeliveryAddressBottomSheet
import com.apparel.offprice.features.address.presentation.component.DeliveryAddressDeleteDialog
import com.apparel.offprice.features.address.presentation.component.DeliveryAddressItemList

@Composable
fun DeliveryAddressScreen(
    viewModel: DeliveryAddressViewModel = hiltViewModel(),
    onNavigateToBack: () -> Unit
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            DeliveryAddressContract.UiEffect.OnNavigateToBack -> {
                onNavigateToBack()
            }

            is DeliveryAddressContract.UiEffect.ShowError -> {}
            DeliveryAddressContract.UiEffect.AddressSaved -> {

            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_delivery_address)) { onNavigateToBack() }
        },
        bottomBar = {
            // Bottom Action Buttons
            BottomSingleActionButton(
                title = stringResource(R.string.label_add_new_address),
                onButtonClicked = {
                    event.invoke(DeliveryAddressContract.UiEvent.OnAddAddressClicked)
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider(thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))
            if (state.deliveryAddress.isNotEmpty()) {
                DeliveryAddressItemList(
                    deliveryAddress = state.deliveryAddress,
                    onEditClicked = {
                        event.invoke(DeliveryAddressContract.UiEvent.OnEditAddress(it))
                    },
                    onDeleteClicked = {
                        event.invoke(DeliveryAddressContract.UiEvent.OnDeleteAddress(it))
                    },
                    modifier = Modifier.weight(1f)
                )
            }
            if (state.isAddAddressOpened) {
                //show AddressBottomSheet
                DeliveryAddressBottomSheet(
                    state = state.addressForm,
                    onEvent = {
                        event.invoke(it)
                    },
                    onDismiss = {
                        event.invoke(DeliveryAddressContract.UiEvent.OnAddAddressDismiss)
                    }
                )
            }

            if (state.isDeleteDialogOpened) { // show Delete Address Dialog
                DeliveryAddressDeleteDialog(
                    onDismiss = {
                        event.invoke(DeliveryAddressContract.UiEvent.OnDeleteDialogDismiss)
                    },
                    onDeleteAddress = {
                        event.invoke(DeliveryAddressContract.UiEvent.OnDeleteAddressConfirm)
                    }
                )
            }
        }
    }
}


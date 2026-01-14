package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.checkout.presentation.components.TopBar
import com.apparel.offprice.features.returnFlow.presentation.component.AddressReturnBottomBar
import com.apparel.offprice.features.returnFlow.presentation.component.ConfirmReturnDialog
import com.apparel.offprice.features.returnFlow.presentation.component.DeliveryAddressItemList
import com.apparel.offprice.features.returnFlow.presentation.component.RefundMethodBottomSheet
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnSuccessDialog

@Composable
fun AddressReturnScreen(
    viewModel: AddressReturnViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onContinue: () -> Unit,
    onAddNewAddress: () -> Unit
) {

    val (state, event, effect) = use(viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            AddressReturnContract.UiEffect.NavigateBack -> onBackClick()
            AddressReturnContract.UiEffect.NavigateNext -> onContinue()
            AddressReturnContract.UiEffect.NavigateToAddAddress -> onAddNewAddress()
            AddressReturnContract.UiEffect.FinishFlow -> onContinue()
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopBar(
                title = stringResource(R.string.address_return).uppercase(),
                onBack = {
                    event(AddressReturnContract.UiEvent.OnCancelClick)
                }
            )
        },
        bottomBar = {
            AddressReturnBottomBar(
                enabled = state.selectedAddressId != null,
                onAddNew = {
                    event(AddressReturnContract.UiEvent.OnAddNewAddressClick)
                },
                onContinue = {
                    event(AddressReturnContract.UiEvent.OnContinueClick)
                }
            )
        }
    ) { padding ->

        DeliveryAddressItemList(
            deliveryAddress = state.addresses,
            selectedAddressId = state.selectedAddressId,
            onAddressSelected = {
                event(AddressReturnContract.UiEvent.OnAddressSelected(it))
            },
            onEditClicked = {
                event(AddressReturnContract.UiEvent.OnEditAddress(it))
            },
            onDeleteClicked = {
                event(AddressReturnContract.UiEvent.OnDeleteAddress(it))
            },
            modifier = Modifier.padding(padding)
        )
    }

    //  Refund Bottom Sheet
    if (state.isRefundSheetOpen) {
        RefundMethodBottomSheet(
            selected = state.selectedRefundMethod,
            onSelect = {
                event(AddressReturnContract.UiEvent.OnRefundMethodSelected(it))
            },
            onReturnItemClick = {
                event(AddressReturnContract.UiEvent.OnReturnItemClick)
            },
            onDismiss = {  // add dismiss callback
                event(AddressReturnContract.UiEvent.OnRefundSheetDismiss)
            }
        )
    }

    //Confirm Dialog
    if (state.showConfirmDialog) {
        ConfirmReturnDialog(
            onYes = {
                event(AddressReturnContract.UiEvent.OnConfirmReturn)
            },
            onNo = {
                event(AddressReturnContract.UiEvent.OnCancelReturn)
            }
        )
    }

    //Success Dialog
    if (state.showSuccessDialog) {
        ReturnSuccessDialog(
            onOkay = {
                event(AddressReturnContract.UiEvent.OnSuccessOkayClick)
            }
        )
    }
}


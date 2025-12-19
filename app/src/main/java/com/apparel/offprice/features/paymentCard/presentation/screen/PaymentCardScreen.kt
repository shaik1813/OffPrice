package com.apparel.offprice.features.paymentCard.presentation.screen

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.BottomSingleActionButton
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.paymentCard.presentation.component.DeletePaymentCardDialog
import com.apparel.offprice.features.paymentCard.presentation.component.PaymentCardBottomSheet
import com.apparel.offprice.features.paymentCard.presentation.component.PaymentCardList
import com.apparel.offprice.features.profile.presentation.screen.profilePassword.showToast

@Composable
fun PaymentCardScreen(
    onNavigateBack: () -> Unit,
    viewModel: PaymentCardViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)
    val context = LocalContext.current

    effect.CollectInLaunchedEffect {
        when (it) {
            PaymentCardContract.UiEffect.OnNavigateBack -> {
                onNavigateBack()
            }

            PaymentCardContract.UiEffect.CardSaved -> {
                showToast(context, "Saved Successfully")
            }

            is PaymentCardContract.UiEffect.ShowError -> {
                showToast(context, it.message)
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(R.string.label_payment_cards),
                onBackPressed = {
                    event(PaymentCardContract.UiEvent.OnBackPressed)
                }
            )
        },
        bottomBar = {
            BottomSingleActionButton(
                title = stringResource(R.string.label_add_new_card),
                onButtonClicked = {
                    event(PaymentCardContract.UiEvent.OnAddCardClicked)
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
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            if (state.paymentCards.isNotEmpty()) {
                PaymentCardList(
                    paymentCards = state.paymentCards,
                    onDeleteClicked = { cardModel ->
                        event(PaymentCardContract.UiEvent.OnDeleteCard(cardModel))
                    },
                    onDefaultChanged = { cardModel ->
                        event(PaymentCardContract.UiEvent.OnDefaultChanged(cardModel))
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        if (state.isAddCardOpened) {
            //show CardBottomSheet
            PaymentCardBottomSheet(
                state = state.cardForm,
                onEvent = event,
                onDismiss = {
                    event.invoke(PaymentCardContract.UiEvent.OnAddCardDismiss)
                }
            )
        }

        if (state.isDeleteDialogOpened) { // show Delete card Dialog
            DeletePaymentCardDialog(
                onDismiss = {
                    event.invoke(PaymentCardContract.UiEvent.OnDeleteDialogDismiss)
                },
                onDeleteCard = {
                    event.invoke(PaymentCardContract.UiEvent.OnDeleteCardConfirm)
                }
            )
        }
    }
}


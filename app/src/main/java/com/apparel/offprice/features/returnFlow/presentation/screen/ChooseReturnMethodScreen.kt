package com.apparel.offprice.features.returnFlow.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.apparel.offprice.features.returnFlow.data.ReturnMethod
import com.apparel.offprice.features.returnFlow.presentation.component.ChooseReturnMethodBottomBar
import com.apparel.offprice.features.returnFlow.presentation.component.ReturnMethodCard

@Composable
fun ChooseReturnMethodScreen(
    viewModel: ChooseReturnMethodViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNavigateToStoreReturn: () -> Unit,
    onNavigateToAddressReturn: () -> Unit
) {

    val (state, event, effect) = use(viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            ChooseReturnMethodContract.UiEffect.NavigateBack -> {
                onBackClick()
            }
            ChooseReturnMethodContract.UiEffect.NavigateToStoreReturn -> {
                onNavigateToStoreReturn()
            }
            ChooseReturnMethodContract.UiEffect.NavigateToAddressReturn -> {
                onNavigateToAddressReturn()
            }
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopBar(
                title = stringResource(R.string.choose_return_method).uppercase(),
                onBack = {
                    event(ChooseReturnMethodContract.UiEvent.OnCancelClick)
                }
            )
        },
        bottomBar = {
            ChooseReturnMethodBottomBar(
                enabled = state.selectedMethod != null,
                onCancel = {
                    event(ChooseReturnMethodContract.UiEvent.OnCancelClick)
                },
                onContinue = {
                    event(ChooseReturnMethodContract.UiEvent.OnContinueClick)
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            ReturnMethodCard(
                method = ReturnMethod.STORE,
                selected = state.selectedMethod == ReturnMethod.STORE,
                onClick = {
                    event(
                        ChooseReturnMethodContract.UiEvent.OnMethodSelected(
                            ReturnMethod.STORE
                        )
                    )
                }
            )

            ReturnMethodCard(
                method = ReturnMethod.ADDRESS,
                selected = state.selectedMethod == ReturnMethod.ADDRESS,
                onClick = {
                    event(
                        ChooseReturnMethodContract.UiEvent.OnMethodSelected(
                            ReturnMethod.ADDRESS
                        )
                    )
                }
            )
        }
    }
}

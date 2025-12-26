package com.apparel.offprice.features.authentication.presentation.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use


@Composable
fun SignupEmptyScreen(onNavigateBack: () -> Unit, viewModel: SignUpViewModel = hiltViewModel()) {

  /*  val (state, event, effect) = use(viewModel = viewModel)

    LaunchedEffect(Unit) {
        event(SignUpContract.UiEvent.OnLoginClick)
    }

    effect.CollectInLaunchedEffect {
        when(it){
            is SignUpContract.UiEffect.Navigate -> {
            }
            is SignUpContract.UiEffect.OnNavigateBack -> {
                onNavigateBack()
            }
        }
    }*/


    /*Box(modifier = Modifier.fillMaxSize()
    ) {
        SignupScreen(
            state = state,
            event = event,
            onItemClick = { event(SignUpContract.UiEvent.OnNavigate(it)) },
            onClose = { event(SignUpContract.UiEvent.OnCloseLogin) }
        )
    }*/
}

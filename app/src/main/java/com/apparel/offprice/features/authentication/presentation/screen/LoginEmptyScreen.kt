package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use


@Composable
fun LoginEmptyScreen(onNavigateBack: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {

    val (state, event, effect) = use(viewModel = viewModel)

    LaunchedEffect(Unit) {
        event(LoginContract.UiEvent.OnLoginClick)
    }

    effect.CollectInLaunchedEffect {
      when(it){
          is LoginContract.UiEffect.Navigate -> {
          }
          is LoginContract.UiEffect.OnNavigateBack -> {
              onNavigateBack()
          }
      }
    }


    Box(modifier = Modifier.fillMaxSize()
       ) {
        LoginScreen(
            isVisible = state.isLoginVisible,
            onItemClick = { event(LoginContract.UiEvent.OnNavigate(it)) },
            onClose = { event(LoginContract.UiEvent.OnCloseLogin) }
        )
    }
}

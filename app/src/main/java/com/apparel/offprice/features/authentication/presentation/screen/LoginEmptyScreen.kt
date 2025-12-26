package com.apparel.offprice.features.authentication.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use


@Composable
fun LoginEmptyScreen(
    onNavigateBack: () -> Unit, onNavigateToOuter: (Any) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {


    var loginVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        loginVisible = true
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginScreen(
            isVisible = loginVisible,
            onItemClick = {/* event(LoginContract.UiEvent.OnNavigate(it))*/ },
            onClose = {
                loginVisible = false
               /* event(LoginContract.UiEvent.OnCloseLogin)*/
            },
            onNavigateBack = { onNavigateBack()}
        )
    }
}

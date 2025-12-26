package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun LoginEmptyScreen(
    onNavigateBack: () -> Unit
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
            onNavigateBack = {
                onNavigateBack() }
        )
    }
}

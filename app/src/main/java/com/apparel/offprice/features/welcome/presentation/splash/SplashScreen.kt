package com.apparel.offprice.features.welcome.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToRegionSelection: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val region by viewModel.regionPreference.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        LaunchedEffect(Unit) {
            delay(1000)
            if (region?.isEmpty() == true) {
                onNavigateToRegionSelection()
            } else {
                onNavigateToHomeScreen()
            }
        }

    }
}
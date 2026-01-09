package com.apparel.offprice.features.customerSupport.presentation.screens.aboutUs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBarWithAction
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use


@Composable
fun AboutUsScreen(
    onNavigateToBack: () -> Unit,
    onSearchClicked: () -> Unit,
    onWishlistClicked: () -> Unit,
    viewModel: AboutUsViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBarWithAction(
                title = stringResource(R.string.label_about_us),
                onBackPressed = { onNavigateToBack() },
                onSearchClicked = { onSearchClicked() },
                onWishlistClicked = { onWishlistClicked() }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {

        }
    }

}
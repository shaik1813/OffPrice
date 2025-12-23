package com.apparel.offprice.features.welcome.presentation.region

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.welcome.presentation.component.RegionSelectionCard

@Composable
fun RegionSelectionScreen(
    onNavigateToNextScreen: () -> Unit,
    viewModel: RegionSelectionViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            RegionSelectionContract.UiEffect.NavigateToNextScreen -> {
                onNavigateToNextScreen()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(75.dp))

        Image(
            painter = painterResource(R.drawable.offprice_logo),
            contentDescription = "OffPrice",
            modifier = Modifier
                .width(150.dp)
                .height(33.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.label_choose_location),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.W600
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.regionList, key = { it.countryCode }) { item ->
                RegionSelectionCard(item) {
                    event.invoke(RegionSelectionContract.UiEvent.OnRegionSelected(it))
                }
            }
        }
    }
}


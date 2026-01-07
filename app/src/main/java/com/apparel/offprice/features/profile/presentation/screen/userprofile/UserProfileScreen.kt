package com.apparel.offprice.features.profile.presentation.screen.userprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.takeInitials
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CircularProgressbar
import com.apparel.offprice.features.profile.presentation.component.ProfileCardItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    onNavigateToBack: () -> Unit,
    onPersonalItemClicked: () -> Unit,
    onMySizeItemClicked: () -> Unit,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            UserProfileContract.UiEffect.OnBackPressed -> {
                onNavigateToBack()
            }

            UserProfileContract.UiEffect.OnMySizeItemClicked -> {
                onMySizeItemClicked()
            }

            UserProfileContract.UiEffect.OnPersonalItemClicked -> {
                onPersonalItemClicked()
            }
        }
    }
    Scaffold(
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_my_account).uppercase()) {
                event.invoke(UserProfileContract.UiEvent.OnBackPressed)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentWindowInsets = WindowInsets(bottom = 0),
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEEEE)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressbar(name = state.username.takeInitials())
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = state.username,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.userEmail,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ProfileCardItem(
                    icon = R.drawable.account_outline_icon,
                    userItem = stringResource(R.string.label_personal),
                    userDescription = stringResource(R.string.label_personal_description),
                    onItemClicked = {
                        event.invoke(UserProfileContract.UiEvent.OnPersonalItemClicked)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                ProfileCardItem(
                    icon = R.drawable.icon_mysize,
                    userItem = stringResource(R.string.label_mysize),
                    userDescription = stringResource(R.string.label_mysize_description),
                    onItemClicked = {
                        event.invoke(UserProfileContract.UiEvent.OnMySizeItemClicked)
                    }
                )
            }
        }
    }
}


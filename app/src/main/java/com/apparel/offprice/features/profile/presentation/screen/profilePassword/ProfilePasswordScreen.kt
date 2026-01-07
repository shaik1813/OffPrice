package com.apparel.offprice.features.profile.presentation.screen.profilePassword

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.AppBasicPasswordField
import com.apparel.offprice.common.component.BottomDoubleActionButton
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.profile.presentation.component.ActionButtonsBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePasswordScreen(
    onNavigateBack: () -> Unit,
    viewModel: ProfilePasswordViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)
    val context = LocalContext.current

    effect.CollectInLaunchedEffect {
        when (it) {
            is ProfilePasswordContract.UiEffect.NavigateBack -> {
                onNavigateBack()
            }

            is ProfilePasswordContract.UiEffect.ShowError -> {
                showToast(context = context, it.message)
            }

            is ProfilePasswordContract.UiEffect.ShowPasswordChangedSuccess -> {
                onNavigateBack()
            }
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_security).uppercase()) {
                onNavigateBack()
            }
        },
        bottomBar = {
            BottomDoubleActionButton(
                leftButtonText = stringResource(R.string.label_cancel).uppercase(),
                rightButtonText = stringResource(R.string.label_save).uppercase(),
                onLeftClick = {
                    event.invoke(ProfilePasswordContract.UiEvent.OnCancelChangePasswordClicked)
                },
                onRightClick = {
                    event.invoke(ProfilePasswordContract.UiEvent.OnChangePasswordClicked)
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEFEFEF)
                    )
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.label_old_password))
                                withStyle(style = SpanStyle(color = redColor)) {
                                    append("*")
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        AppBasicPasswordField(
                            value = state.oldPassword,
                            enabled = true,
                            isVisible = state.isOldPasswordVisible,
                            onValueChange = {
                                event.invoke(ProfilePasswordContract.UiEvent.OnOldPasswordChanged(it))
                            },
                            onIconToggle = {
                                event.invoke(ProfilePasswordContract.UiEvent.ToggleOldPasswordVisibility)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.label_new_password))
                                withStyle(style = SpanStyle(color = redColor)) {
                                    append("*")
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        AppBasicPasswordField(
                            value = state.newPassword,
                            enabled = true,
                            isVisible = state.isNewPasswordVisible,
                            onValueChange = {
                                event.invoke(ProfilePasswordContract.UiEvent.OnNewPasswordChanged(it))
                            },
                            onIconToggle = {
                                event.invoke(ProfilePasswordContract.UiEvent.ToggleNewPasswordVisibility)
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.label_re_enter_password))
                                withStyle(style = SpanStyle(color = redColor)) {
                                    append("*")
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        AppBasicPasswordField(
                            value = state.confirmPassword,
                            enabled = true,
                            isVisible = state.isConfirmPasswordVisible,
                            onValueChange = {
                                event(ProfilePasswordContract.UiEvent.OnConfirmPasswordChanged(it))
                            },
                            onIconToggle = {
                                event(ProfilePasswordContract.UiEvent.ToggleConfirmPasswordVisibility)
                            }
                        )
                    }
                }
            }
        }

    }


}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
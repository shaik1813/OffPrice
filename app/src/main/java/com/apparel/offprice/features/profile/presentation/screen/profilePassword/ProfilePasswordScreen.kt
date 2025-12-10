package com.apparel.offprice.features.profile.presentation.screen.profilePassword

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.OffPriceTheme
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.profile.presentation.screen.myaccounts.ActionButtonsBar

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.label_security).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = "English",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 14.sp
                        )
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(16.dp)
                            .padding(horizontal = 4.dp)
                    )
                    Text(
                        text = "العربية",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp
                        )
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            windowInsets = WindowInsets(0, 0, 0, 0),
        )
        HorizontalDivider(thickness = 1.dp)
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
                            append(stringResource(R.string.password))
                            withStyle(style = SpanStyle(color = redColor)) {
                                append("*")
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = state.newPassword,
                        onValueChange = {
                            event.invoke(ProfilePasswordContract.UiEvent.OnNewPasswordChanged(it))
                        },
                        textStyle = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.75.dp,
                                color = lineColor,
                                shape = MaterialTheme.shapes.small
                            ),
                        enabled = true,
                        shape = MaterialTheme.shapes.small,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (state.isNewPasswordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { event.invoke(ProfilePasswordContract.UiEvent.ToggleNewPasswordVisibility) }) {
                                Icon(
                                    painter = if (state.isNewPasswordVisible)
                                        painterResource(R.drawable.icon_password_visible) else painterResource(
                                        R.drawable.icon_password_hide
                                    ),
                                    contentDescription = null
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.label_confirm_password))
                            withStyle(style = SpanStyle(color = redColor)) {
                                append("*")
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = state.confirmPassword,
                        onValueChange = {
                            event(ProfilePasswordContract.UiEvent.OnConfirmPasswordChanged(it))
                        },
                        textStyle = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.75.dp,
                                color = lineColor,
                                shape = MaterialTheme.shapes.small
                            ),
                        enabled = true,
                        shape = MaterialTheme.shapes.small,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (state.isConfirmPasswordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { event(ProfilePasswordContract.UiEvent.ToggleConfirmPasswordVisibility) }) {
                                Icon(
                                    painter = if (state.isConfirmPasswordVisible)
                                        painterResource(R.drawable.icon_password_visible) else painterResource(
                                        R.drawable.icon_password_hide
                                    ),
                                    contentDescription = null
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )

                }
            }

            //-----------------------Bottom Space------------------------
            Spacer(modifier = Modifier.weight(1f))
            ActionButtonsBar(
                leftButtonText = stringResource(R.string.label_cancel).uppercase(),
                rightButtonText = stringResource(R.string.label_save).uppercase(),
                onLeftClick = {
                    event.invoke(ProfilePasswordContract.UiEvent.OnCancelChangePasswordClicked)
                },
                onRightClick = {
                    event.invoke(ProfilePasswordContract.UiEvent.OnChangePasswordClicked)
                }
            )
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun ProfilePasswordScreenPreview() {
    OffPriceTheme {
        ProfilePasswordScreen(onNavigateBack = {})
    }
}
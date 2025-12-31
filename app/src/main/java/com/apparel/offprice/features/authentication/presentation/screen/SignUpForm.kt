package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicPasswordField
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicPhoneField
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicTextField


@Composable
fun SignUpForm(
    onLoginClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            is SignUpContract.UiEffect.Navigate -> {}
            SignUpContract.UiEffect.OnNavigateBack -> {}
        }
    }


    val phoneFocusRequester = remember { FocusRequester() }


    if (state.showOtpDialog) {
        OTPVerifyDialog(onEditPhone = {
            event(SignUpContract.UiEvent.OnCloseOtp)
            phoneFocusRequester.requestFocus()
        }, onDismiss = { event(SignUpContract.UiEvent.OnCloseOtp) })
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            stringResource(R.string.create_new_account),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 16.sp
        )

        Text(
            stringResource(R.string.signup_detail),
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = inputTextColor,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.email_address))
                withStyle(style = SpanStyle(color = redColor)) {
                    append("*")
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 30.dp, bottom = 12.dp)
        )

        LoginBasicTextField(
            value = state.email,
            enabled = true,
            placeholder = stringResource(R.string.enter_mail),
            onValueChange = { event(SignUpContract.UiEvent.OnValueChangeEmail(it)) },
        )

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.name))
                withStyle(style = SpanStyle(color = redColor)) {
                    append("*")
                }
            },
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )

        LoginBasicTextField(
            value = state.name,
            enabled = true,
            placeholder = stringResource(R.string.enter_name),
            onValueChange = { event(SignUpContract.UiEvent.OnValueChangeName(it)) },
        )

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.password))
                withStyle(style = SpanStyle(color = redColor)) {
                    append("*")
                }
            },
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )

        LoginBasicPasswordField(
            value = state.passwordValue,
            enabled = true,
            isVisible = state.showPassword,
            onValueChange = { event(SignUpContract.UiEvent.OnValueChangePassword(it)) },
            onIconToggle = {
                event(SignUpContract.UiEvent.OnPasswordVisibleToggle)
            }
        )

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.phone_no))
                withStyle(style = SpanStyle(color = redColor)) {
                    append("*")
                }
            },
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )

        LoginBasicPhoneField(
            modifier = Modifier.focusRequester(phoneFocusRequester),
            value = state.phoneNumber,
            enabled = true,
            phoneCode = state.phoneCode,
            onValueChange = {
                event.invoke(
                    SignUpContract.UiEvent.OnPhoneChange(
                        it
                    )
                )
            },
            onCountrySelected = {
                event.invoke(
                    SignUpContract.UiEvent.SelectCountry(
                        it
                    )
                )
            }
        )
        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { event(SignUpContract.UiEvent.OnOpenOtp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(
                stringResource(R.string.continue_txt).toUpperCase(Locale.current),
                color = surfaceColor,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(20.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = null
                ) {
                    onLoginClick()
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.already_account),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
            Text(
                stringResource(R.string.login),
                fontSize = 14.sp,
                color = Color(0XFF9F1D26),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}
package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginGoogleColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.theme.saleEndTextColor
import com.apparel.offprice.features.authentication.presentation.component.AuthButton
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicPasswordField
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicTextField
import com.apparel.offprice.features.cart.presentation.component.CartCheckboxBox
import com.apparel.offprice.routes.AppScreen


@Composable
fun LoginForm(
    state: LoginContract.UiState,
    event: (LoginContract.UiEvent) -> Unit,
    onItemClick: (AppScreen) -> Unit,
    onForgotClick: () -> Unit
) {

    Column {
        Text(
            stringResource(R.string.login_account_header),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge.copy(lineHeight = 19.sp)
        )

        Text(
            stringResource(R.string.login_details),
            fontSize = 12.sp,
            color = inputTextColor,
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 14.sp)
        )

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.email_address_phone))
                withStyle(style = SpanStyle(color = redColor)) {
                    append("*")
                }
            },
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 30.dp, bottom = 12.dp)
        )

        LoginBasicTextField(
            value = state.email,
            enabled = true,
            placeholder = stringResource(R.string.enter_mail),
            onValueChange = { event(LoginContract.UiEvent.OnValueChangeEmail(it)) },
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
            modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
        )

        LoginBasicPasswordField(
            value = state.passwordValue,
            enabled = true,
            isVisible = state.showPassword,
            onValueChange = { event(LoginContract.UiEvent.OnValueChangePassword(it)) },
            onIconToggle = {
                event(LoginContract.UiEvent.OnPasswordVisibleToggle)
            }
        )

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(
                    interactionSource = null,
                    indication = null
                ) {
                    event(LoginContract.UiEvent.OnCheckToggle)
                }) {
                CartCheckboxBox(
                    checked = state.isRememberCheck,
                    onCheckedChange = { event(LoginContract.UiEvent.OnCheckToggle) },
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.size(3.dp))

                Text(
                    stringResource(R.string.remember_me),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onForgotClick()
                    },
                text = stringResource(R.string.forgot_pass),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.size(30.dp))

        AuthButton(
            text = stringResource(R.string.login_caps),
            onButtonClick = {
                onItemClick(AppScreen.OTPScreen)
            })

        Spacer(Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp, start = 40.dp),
                color = Color.Black.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )
            Text(
                " " + stringResource(R.string.signin_with) + " ",
                color = inputTextColor,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 14.sp
            )

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, end = 40.dp),
                color = Color.Black.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )
        }

        Spacer(Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, buttonBorderColor)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    stringResource(R.string.google),
                    style = MaterialTheme.typography.bodyMedium,
                    color = loginGoogleColor,
                    fontSize = 14.sp
                )
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, buttonBorderColor)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook_icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    stringResource(R.string.facebook),
                    style = MaterialTheme.typography.bodyMedium,
                    color = loginGoogleColor,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                stringResource(R.string.dont_have_account) + " ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.clickable {
                    event(LoginContract.UiEvent.OnOpenSignUp)
                },
                text = stringResource(R.string.register_now),
                fontSize = 14.sp,
                color = saleEndTextColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
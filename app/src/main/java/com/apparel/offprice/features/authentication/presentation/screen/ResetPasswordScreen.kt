package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicPasswordField


@Composable
fun ResetPasswordScreen(
    onDismiss: () -> Unit,
    viewModel: ResetPassViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    Box(modifier = Modifier
        .fillMaxSize()
        .navigationBarsPadding() // 👈 important
        .imePadding()
        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        .background(Color.White)
        .padding(horizontal = 16.dp, vertical = 26.dp)){

        Column(modifier = Modifier.align(Alignment.TopStart)) {

            Text(
                text = stringResource(com.apparel.offprice.R.string.reset_your_password),
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(com.apparel.offprice.R.string.forgot_msg_details),
                fontSize = 12.sp,
                color = inputTextColor,
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 14.sp)
            )


            Text(
                buildAnnotatedString {
                    append(stringResource(com.apparel.offprice.R.string.new_password))
                    withStyle(style = SpanStyle(color = redColor)) {
                        append("*")
                    }
                },
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 30.dp, bottom = 12.dp)
            )

            LoginBasicPasswordField(
                value = state.passwordValue,
                enabled = true,
                isVisible = state.showPassword,
                onValueChange = { event(ResetPasswordContract.UiEvent.OnValueChangePassword(it)) },
                onIconToggle = {
                    event(ResetPasswordContract.UiEvent.OnPasswordVisibleToggle)
                }
            )

            Text(
                buildAnnotatedString {
                    append(stringResource(com.apparel.offprice.R.string.reenter_new_password))
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
                value = state.repasswordValue,
                enabled = true,
                isVisible = state.reshowPassword,
                onValueChange = { event(ResetPasswordContract.UiEvent.OnValueChangeRePassword(it)) },
                onIconToggle = {
                    event(ResetPasswordContract.UiEvent.OnRePasswordVisibleToggle)
                }
            )

        }


        Button(
            onClick = { onDismiss() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .height(48.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = stringResource(R.string.reset_password),
                color = Color.White,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}


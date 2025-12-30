package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.errorColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.authentication.presentation.component.LoginBasicTextField
import java.util.Locale.getDefault


@Composable
fun ForgotPasswordScreen(
    onClickLogin: () -> Unit,
    onClickContinue: () -> Unit,
    viewModel: ForgotViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    val focusRequesters = List(6) { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        .background(Color.White)
        .padding(horizontal = 16.dp, vertical = 26.dp)) {
        Column() {
            Text(
                text = stringResource(com.apparel.offprice.R.string.forgot_your_password),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 16.sp, lineHeight = 19.sp,
                    color = saleCardColor
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(com.apparel.offprice.R.string.forgot_msg_details),
                fontSize = 12.sp,
                color = inputTextColor,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                buildAnnotatedString {
                    append(stringResource(R.string.email_address))
                    withStyle(style = SpanStyle(color = redColor)) {
                        append("*")
                    }
                },
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = saleCardColor,
                modifier = Modifier.padding(top = 30.dp, bottom = 12.dp)
            )


            LoginBasicTextField(
                value = state.email,
                enabled = true,
                placeholder = stringResource(R.string.enter_mail),
                onValueChange = {
                    event(ForgotContract.UiEvent.OnValueChangeEmail(it))
                },
            )

        }

        Column(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 14.dp)) {
            Button(
                onClick = { onClickContinue() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = stringResource(R.string.continue_txt).uppercase(getDefault()),
                    color = Color.White,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Text(
                text = stringResource(R.string.back_to_login),
                color = errorColor,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(indication = null, interactionSource = null) { onClickLogin() }
            )
        }

    }

}


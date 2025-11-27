package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.features.authentication.presentation.component.LoginEmailPhoneField
import java.util.Locale.getDefault
import com.apparel.offprice.R
import com.apparel.offprice.routes.AppScreen


@Composable
fun ForgotPasswordScreen(onItemClick: (AppScreen) -> Unit,onDismiss:()->Unit) {

    var otpValues by remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var showResetDialog by remember { mutableStateOf(false) }

    if(showResetDialog){
        ResetPasswordDialog(onDismiss = {showResetDialog = false}) { }
    }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .wrapContentHeight()
            .statusBarsPadding(),
    ) {

            Column() {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close",
                        modifier = Modifier.clickable{
                            onDismiss()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(com.apparel.offprice.R.string.forgot_your_password),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(com.apparel.offprice.R.string.forgot_msg_details),
                    fontSize = 10.sp,
                    color = inputTextColor,
                    style = MaterialTheme.typography.titleSmall)


                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.email_address))
                        withStyle(style = SpanStyle(color = redColor)) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                        style = MaterialTheme.typography.titleSmall,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 30.dp, bottom = 12.dp)
                )

                var email by remember { mutableStateOf("") }

                LoginEmailPhoneField(email, { email = it }, stringResource(R.string.enter_mail))

                Button(
                    onClick = { showResetDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=30.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
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
                    color = loginButtonColor,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top=30.dp).align(Alignment.CenterHorizontally)
                )
            }


    }


}


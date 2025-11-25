package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.customFontFamily
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.loginGoogleColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.authentication.presentation.component.CustomCheckbox
import com.apparel.offprice.features.authentication.presentation.component.LoginEmailPhoneField
import com.apparel.offprice.features.authentication.presentation.component.LoginPasswordField
import com.apparel.offprice.features.welcome.data.model.LocationSelectionItem
import com.apparel.offprice.routes.AppScreen

@Composable
fun LoginScreen(onItemClick: (AppScreen) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 26.dp),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {


                Text(
                    stringResource(R.string.login_account_header),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    stringResource(R.string.login_details),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 10.dp),
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Normal
                )


                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.email_address_phone))
                        withStyle(style = SpanStyle(color= redColor)) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
                )

                var email by remember { mutableStateOf("") }

                LoginEmailPhoneField(email, { email = it }, stringResource(R.string.enter_mail))


                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.password))
                        withStyle(style = SpanStyle(color = redColor)) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
                )

                var passtemp by remember { mutableStateOf("") }
                var showPasswordTemp by remember { mutableStateOf(false) }


                LoginPasswordField(
                    value = passtemp,
                    onValueChange = { passtemp = it },
                    placeholder = stringResource(R.string.password),
                    trailingIcon = {
                        IconButton(onClick = { showPasswordTemp = !showPasswordTemp }) {
                            Icon(
                                imageVector = if (showPasswordTemp)
                                    Icons.Default.Visibility
                                else
                                    Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )


                Spacer(Modifier.height(10.dp))


                var isCheck by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(){
                    CustomCheckbox(isCheck) { isCheck = it }

                    Spacer(modifier = Modifier.size(5.dp))

                    Text(
                        stringResource(R.string.remember_me),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        color = Color.Black, modifier = Modifier.align(
                            Alignment.CenterVertically)
                    )
                    }
                    Text(
                        stringResource(R.string.forgot_pass),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp
                    )
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { onItemClick(AppScreen.OTPScreen) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = loginButtonColor
                    )
                ) {
                    Text(
                        stringResource(R.string.login_caps),
                        color = surfaceColor,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 14.sp
                    )
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f).padding(end=12.dp,start=40.dp),
                        color = inputTextColor,
                        thickness = 0.5.dp
                    )
                    Text(
                        " "+stringResource(R.string.signin_with)+" ",
                        color = inputTextColor,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 14.sp
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f).padding(start=12.dp,end=40.dp),
                        color = inputTextColor,
                        thickness = 0.5.dp
                    )
                }

                Spacer(Modifier.height(20.dp))

                // ---------- SOCIAL BUTTONS ----------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Google
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, buttonBorderColor)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.google),
                            style = MaterialTheme.typography.bodyMedium,
                            color = loginGoogleColor
                        )
                    }

                    // Facebook
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, buttonBorderColor)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.facebook),
                            style = MaterialTheme.typography.bodyMedium,
                            color = loginGoogleColor
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(stringResource(R.string.dont_have_account),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp)
                    Text(modifier = Modifier.clickable(){
                        onItemClick(AppScreen.RegistrationScreen)},
                        text= stringResource(R.string.register_now),
                        fontSize = 14.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

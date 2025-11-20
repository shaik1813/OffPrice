package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.features.authentication.presentation.component.CustomCheckbox
import com.apparel.offprice.features.authentication.presentation.component.NoAnimationOutlinedTextField
import com.apparel.offprice.features.authentication.presentation.component.NoAnimationOutlinedTextFieldWithIcon
import com.apparel.offprice.features.authentication.presentation.component.PasswordInputField

@Preview(showBackground = true)
@Composable
fun LoginScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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

                // ---------- TOP TITLE ----------
                Text(
                    stringResource(R.string.login_account_header)
                    /*buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Yellow)) {
                            append("LOGIN ")
                        }
                        append("YOUR ACCOUNT")
                    }*/,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    stringResource(R.string.login_details),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 10.dp)
                )


                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.email_address))
                        withStyle(style = SpanStyle(color= redColor)) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
                )

                // ---------- EMAIL FIELD ----------
                var email by remember { mutableStateOf("") }

                NoAnimationOutlinedTextField(email, { email = it }, "enter email")


                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.password))
                        withStyle(style = SpanStyle(color = redColor)) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
                )

                // ---------- PASSWORD FIELD ----------
                var password by remember { mutableStateOf("") }
                var showPassword by remember { mutableStateOf(false) }


                /*  NoAnimationOutlinedTextFieldWithIcon(
                      value = password,
                      onValueChange = { password = it },
                      placeholder = "Password *",
                      trailingIcon = {
                          IconButton(onClick = { showPassword = !showPassword }) {
                              Icon(
                                  imageVector = if (showPassword)
                                      Icons.Default.Visibility
                                  else
                                      Icons.Default.VisibilityOff,
                                  contentDescription = null
                              )
                          }
                      }
                  )*/
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(buildAnnotatedString {
                            append(stringResource(R.string.password))
                            withStyle(style = SpanStyle( color = MaterialTheme.colorScheme.tertiary)) {
                                append("*")
                            }
                        })
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                painterResource(
                                    if (showPassword) R.drawable.eye_slash
                                    else R.drawable.eye_slash
                                ),
                                contentDescription = null
                            )
                        }
                    }
                )

                Spacer(Modifier.height(10.dp))

                var isCheck by remember { mutableStateOf(false) }
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomCheckbox(isCheck) { isCheck = it }

                    Text(
                        stringResource(R.string.remember_me), fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        stringResource(R.string.forgot_pass),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }
                // ---------- REMEMBER ME + FORGOT ----------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {}
                        )
                        Text(
                            stringResource(R.string.remember_me), fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        stringResource(R.string.forgot_pass),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }

                Spacer(Modifier.height(20.dp))

                // ---------- LOGIN BUTTON ----------
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(
                        "LOGIN",
                        color = Color.Yellow,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(Modifier.height(20.dp))

                // ---------- DIVIDER ----------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                    Text(
                        "  or sign in with  ",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color.LightGray,
                        thickness = 1.dp
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
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Google")
                    }

                    // Facebook
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Facebook")
                    }
                }

                Spacer(Modifier.height(20.dp))

                // ---------- REGISTER LINK ----------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Don’t have an account?")
                    Text(
                        " Register Now",
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

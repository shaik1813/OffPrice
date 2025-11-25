package com.apparel.offprice.features.authentication.presentation.screen

import android.util.Log
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.authentication.presentation.component.CustomCheckbox
import com.apparel.offprice.features.authentication.presentation.component.SignupEmailField
import com.apparel.offprice.features.authentication.presentation.component.SignupNameField
import com.apparel.offprice.features.authentication.presentation.component.SignupPasswordField
import com.apparel.offprice.features.authentication.presentation.component.SignupPhoneField

@Preview(showBackground = true)
@Composable
fun SignupScreen() {

    Log.e("signupPage","working")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Spacer(modifier = Modifier.size(10.dp))

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
                    stringResource(R.string.create_new_account),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 16.sp
                )

                Text(
                    stringResource(R.string.signup_detail),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color =inputTextColor,
                    modifier = Modifier.padding(top = 10.dp)
                )


                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.email_address))
                        withStyle(style = SpanStyle(color= redColor)) {
                            append("*")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
                )

                var email by remember { mutableStateOf("") }

                SignupEmailField(email, { email = it }, stringResource(R.string.enter_mail))



                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.name))
                        withStyle(style = SpanStyle(color = redColor)) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
                )
                var name by remember { mutableStateOf("") }

                SignupNameField(name, { name = it }, stringResource(R.string.enter_name))


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

                SignupPasswordField(
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

                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.phone_no))
                        withStyle(style = SpanStyle(color= redColor)) {
                            append("*") } },
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                )

                SignupPhoneField()

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(
                        stringResource(R.string.continue_txt),
                        color = surfaceColor,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 14.sp
                    )
                }

                Spacer(Modifier.height(20.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(stringResource(R.string.already_account),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp)
                    Text(
                        stringResource(R.string.login),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

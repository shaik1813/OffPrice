package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.otpLineColor
import com.apparel.offprice.common.theme.saleCardColor

@Composable
fun OTPVerifyScreen(onVerifyClick:() -> Unit, onDismiss: () -> Unit) {

    var otpValues by remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f),
    ) {
        Row(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.close_24px),
                contentDescription = "close",
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 10.dp)
                    .size(22.dp)
                    .clickable { onDismiss() }
            )
        }

        Column(modifier = Modifier.align(Alignment.TopStart)) {

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = stringResource(R.string.verify_account),
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleLarge.copy(lineHeight = 19.sp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.verify_code_msg),
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    color = inputTextColor,
                    style = MaterialTheme.typography.labelMedium
                )

                Image(
                    painter = painterResource(R.drawable.icon_flag_uae),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(18.dp)
                )
                Text(
                    text = "+971 54843324",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp),
                    color = saleCardColor
                )

                Image(
                    painter = painterResource(R.drawable.icon_edit_address),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }


            Spacer(modifier = Modifier.height(35.dp))


            // OTP input boxes

            Row(modifier = Modifier.fillMaxWidth()) {


                Row(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                    otpValues.forEachIndexed { index, value ->

                        BasicTextField(
                            value = value,
                            onValueChange = { newValue ->

                                val updated = otpValues.toMutableList()

                                // When deleting character
                                if (newValue.isEmpty() && otpValues[index].isNotEmpty()) {
                                    updated[index] = ""
                                    otpValues = updated
                                    focusRequesters[index].requestFocus() // stay here
                                }

                                // When typing new digit
                                else if (newValue.length == 1 && newValue.all { it.isDigit() }) {
                                    updated[index] = newValue
                                    otpValues = updated

                                    // Move to next field
                                    if (index < otpValues.lastIndex) {
                                        focusRequesters[index + 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(30.dp)
                                .padding(bottom = 4.dp)
                                .focusRequester(focusRequesters[index])
                                .onKeyEvent { event ->
                                    if (event.type == KeyEventType.KeyDown && event.key == Key.Backspace) {

                                        // if already empty -> move to previous field
                                        if (otpValues[index].isEmpty() && index > 0) {
                                            focusRequesters[index - 1].requestFocus()
                                        }
                                        true
                                    } else false
                                },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                color = saleCardColor, textAlign = TextAlign.Center
                            ),
                            decorationBox = { innerTextField ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    innerTextField()
                                    Spacer(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(otpLineColor)
                                    )
                                }
                            }
                        )

                    }
                }



                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .weight(0.2f)

                ) {

                    Text(
                        text = "00:30",
                        fontSize = 12.sp,
                        color = otpLineColor,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                }
            }

            Text(
                text = stringResource(com.apparel.offprice.R.string.resend_code),
                fontSize = 14.sp, color = inputTextColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.End).padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { onVerifyClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = stringResource(R.string.verify),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White, fontSize = 14.sp)
            }

        }


    }


}


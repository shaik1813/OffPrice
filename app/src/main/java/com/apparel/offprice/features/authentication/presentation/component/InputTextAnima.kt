package com.apparel.offprice.features.authentication.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun InputTextAnim() {
    // ---------- PASSWORD FIELD ----------
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }


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
}
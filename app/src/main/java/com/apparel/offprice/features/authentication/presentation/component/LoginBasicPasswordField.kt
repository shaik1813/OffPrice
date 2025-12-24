package com.apparel.offprice.features.authentication.presentation.component


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.lineColor

@Composable
fun LoginBasicPasswordField(
    value: String,
    enabled: Boolean,
    placeholder: String = stringResource(R.string.enter_password),
    isVisible: Boolean,
    onValueChange: (String) -> Unit,
    onIconToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = MaterialTheme.shapes.small)
            .height(42.dp)
            .border(
                color = lineColor,
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        BasicTextField(
            value = value,
            onValueChange = { if (enabled) onValueChange(it) },
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            enabled = enabled,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            visualTransformation = if (isVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 0.75.dp,
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodySmall,
                            color = lineColor
                        )
                    }
                    innerTextField()
                    IconButton(
                        onClick = { onIconToggle() },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = if (isVisible)
                                painterResource(R.drawable.icon_password_visible) else painterResource(
                                R.drawable.icon_password_hide
                            ),
                            contentDescription = null,

                            )
                    }
                }
            }
        )
        if (!enabled) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = MaterialTheme.shapes.small
                    )
            )
        }
    }
}
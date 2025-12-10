package com.apparel.offprice.features.profile.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.lineColor

/**
 * A reusable composable that displays a label and an outlined text field.
 *
 * @param label The text to be displayed as the label for the field.
 * @param value The current value of the text field.
 * @param enabled A boolean to determine if the text field is enabled for user input.
 * @param onValueChange A lambda function to be invoked when the value of the text field changes.
 */
@Composable
fun LabeledField(
    label: String,
    value: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(Modifier.height(6.dp))
    OutlinedTextField(
        value = value,
        onValueChange = { if (enabled) onValueChange(it) },
        textStyle = MaterialTheme.typography.bodySmall,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.75.dp,
                color = lineColor,
                shape = MaterialTheme.shapes.small
            ),
        shape = MaterialTheme.shapes.small,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            disabledContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
    Spacer(Modifier.height(4.dp))

}

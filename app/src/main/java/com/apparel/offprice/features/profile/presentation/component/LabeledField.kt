package com.apparel.offprice.features.profile.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.component.AppBasicTextField

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
    placeholder: String = "",
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(Modifier.height(6.dp))
    AppBasicTextField(
        value = value,
        enabled = enabled,
        placeholder = placeholder,
        onValueChange = { if (enabled) onValueChange(it) },
    )
    Spacer(Modifier.height(4.dp))
}



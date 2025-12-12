package com.apparel.offprice.features.profile.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.lineColor

/**
 * A composable function that displays a dropdown menu for category selection.
 * It includes a label, the main text field showing the selected category, and a dropdown list of options.
 *
 * @param label The text to be displayed as a label above the dropdown.
 * @param selectedCategory The currently selected category to be displayed in the text field.
 * @param enabled A boolean to determine if the dropdown is enabled for user interaction.
 * @param onCategorySelected A lambda function that is invoked when a user selects a new category from the dropdown list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    label: String,
    categoriesList: List<String>,
    selectedCategory: String,
    enabled: Boolean,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(Modifier.height(6.dp))

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (enabled) { expanded = it }
        }
    ) {
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.75.dp,
                    color = lineColor,
                    shape = MaterialTheme.shapes.small
                )
                .menuAnchor(),
            trailingIcon = {
                if (enabled) {
                    Icon(
                        painter = painterResource(id = R.drawable.down_arrow),
                        contentDescription = "Dropdown Arrow",
                        modifier = Modifier.size(16.dp)
                    )
                }
            },
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
                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize(true),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            categoriesList.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category, style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}
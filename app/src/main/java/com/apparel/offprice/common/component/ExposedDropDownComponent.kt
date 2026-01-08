package com.apparel.offprice.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.lineColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropDownComponent(
    categoriesList: List<String>,
    selectedCategory: String,
    placeholder: String = "",
    enabled: Boolean,
    modifier:Modifier = Modifier,
    onCategorySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (enabled) { expanded = it }
        }
    ) {
        Box(
            modifier = modifier
                .background(Color.White,shape = MaterialTheme.shapes.small)
                .height(42.dp)
        ) {
            BasicTextField(
                value = selectedCategory,
                onValueChange = { },
                textStyle = MaterialTheme.typography.bodySmall,
                singleLine = true,
                enabled = enabled,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxSize()
                    .menuAnchor(),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = 0.75.dp,
                                color = borderColor,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (selectedCategory.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.bodySmall,
                                color = lineColor
                            )
                        }
                        innerTextField()
                        if (enabled) {
                            Icon(
                                painter = painterResource(id = R.drawable.down_arrow),
                                contentDescription = "Dropdown Arrow",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .size(16.dp)
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
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize(true),
            containerColor = MaterialTheme.colorScheme.background
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
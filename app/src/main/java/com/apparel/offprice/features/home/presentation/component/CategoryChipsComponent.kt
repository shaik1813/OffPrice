package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//@Composable
//fun CategoryChips(selected: SearchCategory, onSelect: (SearchCategory) -> Unit) {
//    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        SearchCategory.entries.forEach { category ->
//            FilterChip(
//                selected = category == selected,
//                onClick = { onSelect(category) },
//                label = {
//                    Text(
//                        category.title,
//                        style = MaterialTheme.typography.titleMedium,
//                        fontSize = 12.sp,
//                        letterSpacing = 0.sp
//                    )
//                },
//                colors = FilterChipDefaults.filterChipColors(
//                    selectedContainerColor = MaterialTheme.colorScheme.primary,
//                    selectedLabelColor = Color.White
//                ),
//                modifier = Modifier
//                    .padding(vertical = 4.dp)
//            )
//        }
//    }
//}


@Composable
fun TagList(
    items: List<String>,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    removable: Boolean = false,
    onRemove: (String) -> Unit = {},
    onClick: (String) -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items.forEach {
            AssistChip(
                onClick = { onClick(it) },
                label = { Text(text = it, style = MaterialTheme.typography.labelMedium) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                leadingIcon = {
                    icon?.let { ic ->
                        Icon(
                            imageVector = ic,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                trailingIcon = {
                    if (removable) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable { onRemove(it) })
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
        }
    }
}


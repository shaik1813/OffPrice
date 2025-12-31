package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.utils.toComposeColorSafe
import com.apparel.offprice.features.plp.data.model.FilterItem

@Composable
fun ColorChip(
    filter: FilterItem,
    onFilterItemClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .then(
                if (filter.isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable {
                onFilterItemClick()
            }
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    filter.hexColor?.toComposeColorSafe() ?: Color.Black,
                    RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(4.dp)
                )
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = filter.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ClothingSizeChip(
    filter: FilterItem,
    onFilterItemClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(8.dp))
            .then(
                if (filter.isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable {
                onFilterItemClick()
            }
    ) {
        Text(
            text = filter.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterStrip(onFilterClick: () -> Unit, onSortClick: () -> Unit) {
    val borderColor = Color(0xFFE0E0E0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Top line
        HorizontalDivider(color = borderColor, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilterCell(
                icon = R.drawable.filter_icon,   // your filter icon
                label = "Filter",
                onClick = { onFilterClick() }
            )

            VerticalDivider(color = borderColor, thickness = 1.dp)

            FilterCell(
                icon = R.drawable.sort_icon,     // your sort icon
                label = "Sort",
                onClick = { onSortClick() }
            )

            VerticalDivider(color = borderColor, thickness = 1.dp)

            FilterCell(
                icon = R.drawable.best_price_outline_icon,    // your brand icon
                label = "Brand",
                count = 4,
                showDropdown = true,
                onClick = { onFilterClick() }
            )

            VerticalDivider(color = borderColor, thickness = 1.dp)

            FilterCell(
                icon = R.drawable.best_price_outline_icon,     // your size icon
                label = "Size",
                count = 2,
                showDropdown = true,
                onClick = { onFilterClick() }
            )
        }

        // Bottom line (optional, looks closer to your screenshot)
        HorizontalDivider(color = borderColor, thickness = 1.dp)
    }
}

@Composable
fun RowScope.FilterCell(
    icon: Int,
    label: String,
    count: Int = 0,
    showDropdown: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(18.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        if (count > 0) {
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(Color(0xFFFF5D7A), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (showDropdown) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FilterStripPreview() {
    FilterStrip(onFilterClick = {}, onSortClick = {})
}
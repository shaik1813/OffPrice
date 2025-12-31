package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.badgeColor
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.features.plp.data.model.FilterType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterStrip(
    brandCount : Int,
    sizeCount : Int,
    onFilterClick: (FilterType) -> Unit,
    onSortClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        FilterCell(
            icon = R.drawable.filter_icon,
            label = "Filter",
            count = 0,
            showDropdown = false,
            onClick = { onFilterClick(FilterType.BRAND) }
        )

        FilterCell(
            icon = R.drawable.sort_icon,
            label = "Sort",
            count = 0,
            showDropdown = false,
            onClick = { onSortClick() }
        )

        FilterCell(
            icon = null,
            label = "Brand",
            count = brandCount,
            showDropdown = true,
            onClick = { onFilterClick(FilterType.BRAND) }
        )

        FilterCell(
            icon = null,
            label = "Size",
            count = sizeCount,
            showDropdown = true,
            onClick = { onFilterClick(FilterType.CLOTHING_SIZE) }
        )
    }

}

@Composable
fun RowScope.FilterCell(
    icon: Int?,
    label: String,
    count: Int = 0,
    showDropdown: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .border(BorderStroke(1.dp, borderColor), RectangleShape)
            .padding(horizontal = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(18.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
        )
        if (count != 0){
            BadgedBox(badge = {
                Badge(
                    containerColor = badgeColor,
                    contentColor = Color.White
                ) { Text("$count") }
            }, modifier = Modifier.align(Alignment.Top).padding(top = 6.dp)) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        if (showDropdown){
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = "$label dropdown",
                tint = Color(0XFF292D32)
            )
        }
    }
}

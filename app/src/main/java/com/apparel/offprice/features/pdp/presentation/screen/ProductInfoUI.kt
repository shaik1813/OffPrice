package com.apparel.offprice.features.pdp.presentation.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.loginButtonColor


@Composable
fun ProductInfoUI() {

    val items = listOf(
        stringResource(com.apparel.offprice.R.string.product_highlights) to "Add Some New Styles To Your Wardrobe With This Joggers Designed For More Refined And Modern Stylish Look",
        stringResource(com.apparel.offprice.R.string.details) to "Content",
        stringResource(com.apparel.offprice.R.string.shipping_and_returns) to " Content."
    )
    Log.e("checkcard", "ProductInfoUI  "  + " , ")

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEachIndexed { index, pair ->
            ExpandableRow(
                index,
                title = pair.first,
                description = pair.second,
                showDivider = true
            )
        }
    }
}

@Composable
fun ExpandableRow(
    pos: Int,
    title: String,
    description: String,
    showDivider: Boolean
) {

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium,
                color = loginButtonColor
            )

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                tint = loginButtonColor,
                contentDescription = null
            )
        }

        AnimatedVisibility(expanded) {
            if (pos == 1) ProductSpecificationUI()
            else if (pos == 2) {
                ReturnExchangeSection()
            } else Text(
                text = description,
                fontSize = 11.sp,
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF545454),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        if (showDivider) {
            HorizontalDivider(modifier = Modifier.graphicsLayer(alpha = 1f),color = Color(0xFF6B6B6B), thickness = 1.dp)
        }
    }
}

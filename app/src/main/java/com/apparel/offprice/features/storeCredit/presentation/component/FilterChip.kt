package com.apparel.offprice.features.storeCredit.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup

@Composable
fun FilterChip(
    modifier: Modifier,
    text: String,
    selected: Boolean,
    trailingIcon: Int? = null,
    onClick: () -> Unit,
) {
    var showTooltip by remember { mutableStateOf(false) }

    val background = if (selected) Color(0xFFA2050D) else Color.Transparent
    val contentColor = if (selected) Color.White else Color(0xFF141414)
    Surface(
        modifier = modifier
            .height(36.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(if (selected) 8.dp else 6.dp),
        color = background,
        border = if (selected) BorderStroke(1.dp, Color(0xFFA2050D))
        else BorderStroke(1.dp, Color.Transparent),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp),
                color = contentColor,
            )

            trailingIcon?.let {
                Spacer(modifier = Modifier.width(6.dp))

                Box {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "Info",
                        modifier = Modifier
                            .size(14.dp)
                            .clickable(
                                indication = null, //no ripple
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                showTooltip = true
                            },
                        tint = contentColor
                    )

                    // 🔹 Tooltip
                    if (showTooltip) {
                        PickupStoreTooltip {
                            showTooltip = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PickupStoreTooltip(onDismiss: () -> Unit) {
    Popup(
        alignment = Alignment.TopCenter,
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "SHOP ONLINE AND COLLECT FROM YOUR NEAREST STORE",
                maxLines = 2,
                color = Color.White,
                fontSize = 11.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

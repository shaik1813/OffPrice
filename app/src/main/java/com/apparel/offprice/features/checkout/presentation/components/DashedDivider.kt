package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun DashedDivider(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFE0E0E0)
) {
    Canvas(
        modifier = modifier
            .height(1.dp)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 4f), 0f)
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            pathEffect = pathEffect,
            strokeWidth = 1.dp.toPx()
        )
    }
}


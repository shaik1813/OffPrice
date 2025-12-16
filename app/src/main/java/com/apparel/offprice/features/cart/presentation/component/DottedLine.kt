package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.lineColor


@Composable
fun DottedLine(
    modifier: Modifier = Modifier,
    color: Color = Color(0x1A666666),
    dotRadius: Dp = 1.5.dp,
    spaceBetween: Dp = 1.2.dp
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(0.7.dp)
    ) {
        val dotRadiusPx = dotRadius.toPx()
        val spacePx = spaceBetween.toPx()
        var x = 0f

        while (x < size.width) {
            drawCircle(
                color = color,
                radius = dotRadiusPx,
                center = Offset(x, size.height / 2)
            )
            x += dotRadiusPx * 2 + spacePx
        }
    }
}

package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedLine() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .shadow(5.dp) // 👈 Only top shadow
            .background(Color.Transparent)
    )
}

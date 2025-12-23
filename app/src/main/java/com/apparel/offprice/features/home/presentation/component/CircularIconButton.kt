package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleIconButton(
    icon: Int,
    contentDescription: String,
    onClick: () -> Unit,
    badgeCount: Int = 0
) {
    Box(contentAlignment = Alignment.TopEnd) {

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(44.dp)
                .border(
                    width = 1.5.dp,
                    color = Color(0xFFE0E0E0), // light grey outline
                    shape = CircleShape
                )
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(22.dp),
                tint = Color(0xFF4A4A4A)
            )
        }

        // OPTIONAL BADGE (wishlist count)
        if (badgeCount > 0) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(Color(0xFFFF5D7A), CircleShape)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeCount.toString(),
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


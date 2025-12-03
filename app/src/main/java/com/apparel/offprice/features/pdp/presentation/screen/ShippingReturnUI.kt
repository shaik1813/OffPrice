package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReturnExchangeSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        InfoCard(Modifier.weight(1f)
            .height(110.dp),
            title = "Returnable",
            subText = "Hassle Free Return\nWithin 20 Days",
            icon = Icons.Default.Undo
        )

        InfoCard(Modifier.weight(1f)
            .height(110.dp),
            title = "Exchangeable",
            subText = "Place An Exchange\nWithin 14 Days",
            icon = Icons.Default.Autorenew
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = buildAnnotatedString {
            append("Learn More About Our Return And Exchange Policy ")
            withStyle(style = SpanStyle(color = Color(0xFF007AFF), fontWeight = FontWeight.Bold)) {
                append("Here")
            }
        },
        fontSize = 13.sp,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}


@Composable
fun InfoCard(modifier:Modifier, title: String, subText: String, icon: ImageVector) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFCACACA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subText,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

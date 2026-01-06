package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.primaryColor


@Composable
fun WhyTamaraDetailCard() {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Why Tamara?",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
            color = primaryColor,
            fontSize = 15.sp
        )
        WhyTamaraRow()
    }
}

@Composable
fun WhyTamaraRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WhyTabbyItem(
            icon = R.drawable.why_tabicon1,   // 100% buyer protection
            text = "100%\nbuyer protection"
        )

        WhyTabbyItem(
            icon = R.drawable.why_tabicon1, // Sharia compliant
            text = "Sharia\ncompliant"
        )

        WhyTabbyItem(
            icon = R.drawable.why_tabicon1,   // No late fees
            text = "No late\nfees"
        )
    }
}


@Composable
fun WhyTabbyItem(icon: Int, text: String) {
    Column {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(borderColor)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500)),
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}
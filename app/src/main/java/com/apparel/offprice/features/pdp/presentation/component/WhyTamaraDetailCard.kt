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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
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
            modifier = Modifier.weight(1f),
            icon = R.drawable.why_tabicon1,   // 100% buyer protection
            text = "100%\nbuyer protection"
        )

        WhyTabbyItem(
            modifier = Modifier.weight(1f),
            icon = R.drawable.why_tabicon1, // Sharia compliant
            text = "Sharia\ncompliant"
        )

        WhyTabbyItem(
            modifier = Modifier.weight(1f),
            icon = R.drawable.why_tabicon1,   // No late fees
            text = "No late\nfees"
        )
    }

    Text(
        text = "Payment plans shown are estimates. Actual offers may vary based on your eligibility and order details. Not all merchants or products qualify for every plan, including Tamara’s long-term financing options. Approval is subject to eligibility checks and may require a down payment. Final terms, including monthly payment amounts, may change after checkout review and may exclude taxes, shipping, or other charges. For more information, see our Terms & Conditions",
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(400)),
        color = nonreturnTxtColor,
        modifier = Modifier.padding(top = 14.dp)
    )
}


@Composable
fun WhyTabbyItem(modifier: Modifier, icon: Int, text: String) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(borderColor)
                .align(Alignment.CenterHorizontally)
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
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
package com.apparel.offprice.features.checkout.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PriceBreakdownCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {

        PriceRow("Subtotal (3 items)", "105.00")
        PriceRow("Discount (Auto coupon)", "- 5.00", positive = false)
        PriceRow("Shipping Fee", "14.00")
        Divider(Modifier.padding(vertical = 8.dp))
        PriceRow("Total (Incl VAT)", "107.00", bold = true)
        PriceRow("CA Points Used - 1000", "- 5.00", positive = false)
        PriceRow("Store Credits Used", "- 5.00", positive = false)

        Spacer(modifier = Modifier.height(12.dp))
        PriceRow("GRAND TOTAL", "97.00", bold = true)
    }
}

@Composable
fun PriceRow(label: String, amount: String, bold: Boolean = false, positive: Boolean = true) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Normal,style = MaterialTheme.typography.bodyMedium)
        Text(
            amount,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium,
            color =
                if (!positive) Color(0xFF00A400)
                else Color.Black
        )
    }
}

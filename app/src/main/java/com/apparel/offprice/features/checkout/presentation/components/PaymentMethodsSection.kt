package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun PaymentMethodsSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {

        PaymentOptionRow(
            title = "HSBC Ending with 6766",
            subtitle = "CVV is not required for this saved card",
            trailingIcon = R.drawable.hsbc_1,
            isSelected = true
        )

        Spacer(Modifier.height(12.dp))

        PaymentOptionRow(
            title = "Emirates NBD Ending with 2694",
            trailingIcon = R.drawable.hsbc_1
        )

        PaymentOptionRow(
            title = "Credit / Debit Card",
            trailingIcon = R.drawable.hsbc_1
        )

        PaymentOptionRow(
            title = "Apple Pay",
            trailingIcon = R.drawable.hsbc_1
        )

        PaymentOptionRow(
            title = "Cash On Delivery"
        )
    }
}


@Composable
fun PaymentOptionRow(
    title: String,
    subtitle: String? = null,
    trailingIcon: Int? = null,
    isSelected: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = isSelected,
            onClick = {}
        )

        Spacer(Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )

            subtitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        trailingIcon?.let {
            Image(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

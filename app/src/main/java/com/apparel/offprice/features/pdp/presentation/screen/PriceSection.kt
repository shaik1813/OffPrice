package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.offerTextColor


@Composable
fun PriceSection() {
    Row(modifier = Modifier.padding(top = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "D 35.00", fontSize = 16.sp, color = loginButtonColor,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "D 173.00", fontSize = 12.sp, color = buttonBorderColor,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "-90% OFF", fontSize = 12.sp, color = offerTextColor,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.size(8.dp))

        VerticalDivider(
            modifier = Modifier
                .height(18.dp)
                .width(1.dp),
            color = backgroundColor
        )

        Spacer(modifier = Modifier.size(6.dp))

        Text(
            text = "(Incl.VAT)", fontSize = 12.sp, color = buttonBorderColor,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
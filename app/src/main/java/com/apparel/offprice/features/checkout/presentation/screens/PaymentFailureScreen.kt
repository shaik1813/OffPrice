package com.apparel.offprice.features.checkout.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.features.checkout.data.PaymentResult

@Composable
fun PaymentFailureScreen(
    result: PaymentResult.Failure,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(48.dp))

        Icon(
            painter = painterResource(R.drawable.error_x_icon),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(64.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text("Order Failed", fontWeight = FontWeight.Bold)

        Text(
            text = "Your order couldn’t be completed this time",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("TRY AGAIN")
        }
    }
}

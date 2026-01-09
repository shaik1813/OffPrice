package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.apparel.offprice.R


@Composable
fun PaymentCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Left text
            Column {
                Text(
                    text = "Pay 4 interest-free payments of",
                    fontSize = 12.sp,
                    color = inputTextColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "฿ 45.00",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleLarge,
                    color = loginButtonColor
                )
            }

            // Right logos
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

               Image(painter = painterResource(R.drawable.tabby_payicon),
                   contentDescription = null,
                   modifier = Modifier.width(59.dp).height(23.dp))

                Image(painter = painterResource(R.drawable.tamara_payicon),
                    contentDescription = null,
                    modifier = Modifier.width(65.dp).height(23.dp))

            }
        }
    }
}



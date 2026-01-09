package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.checkout.data.CheckoutStep
import com.apparel.offprice.features.checkout.presentation.screens.CheckOutContract

@Composable
fun BottomBar(
    onSave: () -> Unit,
    buttonText: String,
    showArrow: Boolean,
    grandTotal: Double,
    onOpenAddressSheet: () -> Unit,
    state: CheckOutContract.UiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {

        if (state.checkoutStep == CheckoutStep.SUMMARY && state.selectedAddress != null) {
            SelectedAddressRow(
                address = state.selectedAddress,
                onClick = onOpenAddressSheet
            )

            Spacer(modifier = Modifier.height(12.dp))
        }


        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = buttonText,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )

                // 👉 SHOW ICON ONLY FOR SAVE ADDRESS
                if (showArrow) {
                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        painter = painterResource(R.drawable.icon_currency_uae),
                        contentDescription = null,
                        modifier = Modifier
                            .width(12.dp)
                            .height(10.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = grandTotal.toString(),
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                }
            }
        }
    }
}


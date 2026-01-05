package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.checkout.presentation.screens.CheckOutContract

@Composable
fun PaymentMethodsSection(
    state: CheckOutContract.UiState,
    event: (CheckOutContract.UiEvent) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E5E5))
    ) {
        Column {

            // 🔹 SAVED PAYMENT
            PaymentOptionRow(
                title = "Saved Payment Option",
                selected = state.selectedPayment == PaymentMethod.SAVED,
                onClick = {
                    event(CheckOutContract.UiEvent.OnPaymentMethodClicked(PaymentMethod.SAVED))
                }
            )

            AnimatedVisibility(state.expandedPayment == PaymentMethod.SAVED) {
                SavedPaymentExpanded(state, event)
            }

            HorizontalDivider()

            // 🔹 CREDIT / DEBIT CARD
            PaymentOptionRow(
                title = "Credit / Debit Card",
                trailingLogo = R.drawable.visa_icon,
                selected = state.selectedPayment == PaymentMethod.CARD,
                onClick = {
                    event(CheckOutContract.UiEvent.OnPaymentMethodClicked(PaymentMethod.CARD))
                }
            )

            AnimatedVisibility(state.expandedPayment == PaymentMethod.CARD) {
                CardPaymentExpanded(state, event)
            }

            HorizontalDivider()

            // 🔹 TAMARA
            PaymentOptionRow(
                title = "Pay in 4. No interest, no fees",
                trailingLogo = R.drawable.tamara_icon,
                selected = state.selectedPayment == PaymentMethod.TAMARA,
                onClick = {
                    event(CheckOutContract.UiEvent.OnPaymentMethodClicked(PaymentMethod.TAMARA))
                }
            )

            AnimatedVisibility(state.expandedPayment == PaymentMethod.TAMARA) {
                InstallmentExpanded(provider = "Tamara", state, event)
            }

            HorizontalDivider()

            // 🔹 TABBY
            PaymentOptionRow(
                title = "Pay in 4. No interest, no fees",
                trailingLogo = R.drawable.tabby_icon,
                selected = state.selectedPayment == PaymentMethod.TABBY,
                onClick = {
                    event(CheckOutContract.UiEvent.OnPaymentMethodClicked(PaymentMethod.TABBY))
                }
            )

            AnimatedVisibility(state.expandedPayment == PaymentMethod.TABBY) {
                InstallmentExpanded(provider = "Tabby", state, event)
            }

            HorizontalDivider()

            // 🔹 GOOGLE PAY
            PaymentOptionRow(
                title = "Google Pay",
                trailingLogo = R.drawable.gpay_icon,
                selected = state.selectedPayment == PaymentMethod.GOOGLE_PAY,
                onClick = {
                    event(CheckOutContract.UiEvent.OnPaymentMethodClicked(PaymentMethod.GOOGLE_PAY))
                }
            )

            HorizontalDivider()

            // 🔹 CASH ON DELIVERY
            PaymentOptionRow(
                title = "Cash On Delivery",
                selected = state.selectedPayment == PaymentMethod.COD,
                onClick = {
                    event(CheckOutContract.UiEvent.OnPaymentMethodClicked(PaymentMethod.COD))
                }
            )
        }
    }
}




@Composable
fun PaymentOptionRow(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingLogo: Int? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )

        trailingLogo?.let {
            Image(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier.height(20.dp)
            )
        }
    }
}


@Composable
fun SavedPaymentExpanded(state: CheckOutContract.UiState, event: (CheckOutContract.UiEvent) -> Unit) {
    Column(Modifier.padding(12.dp)) {

        PaymentCardRow(
            title = "HSBC Ending with 6766",
            subtitle = "CVV is not required for this secured card",
            logo = R.drawable.hsbc_1
        )

        Spacer(Modifier.height(12.dp))
        PayButton(97.00, event)

        Spacer(Modifier.height(8.dp))

        PaymentCardRow(
            title = "Emirates NBD Ending with 2694",
            subtitle = "",
            logo = R.drawable.emirates_nbd_icon
        )
    }
}
@Composable
fun CardPaymentExpanded(state: CheckOutContract.UiState, event: (CheckOutContract.UiEvent) -> Unit) {
    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Card Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Row {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("MM/YY") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(12.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("CVV") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(16.dp))
        PayButton(97.00, event)
    }
}
@Composable
fun InstallmentExpanded(provider: String, state: CheckOutContract.UiState, event: (CheckOutContract.UiEvent) -> Unit) {
    Column(Modifier.padding(16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(4) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        progress = (it + 1) / 4f,
                        modifier = Modifier.size(36.dp),
                        strokeWidth = 3.dp
                    )
                    Text("฿ 35.00", fontSize = 12.sp)
                    Text(
                        when (it) {
                            0 -> "Today"
                            else -> "In ${it} Month"
                        },
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        PayButton(97.00, event)
    }
}











@Composable
fun PaymentCardRow(
    title: String,
    subtitle: String,
    logo: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF7F7F7))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )

            if (subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(logo),
            contentDescription = null,
            modifier = Modifier.height(22.dp)
        )
    }
}


@Composable
fun PayButton(amount: Double, event: (CheckOutContract.UiEvent) -> Unit) {
    Button(
        onClick = {
            event(CheckOutContract.UiEvent.OnPayClicked)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = "PAY  ฿ ${"%.2f".format(amount)}")
    }
}



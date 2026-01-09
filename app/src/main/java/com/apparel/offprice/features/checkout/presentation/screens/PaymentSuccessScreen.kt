package com.apparel.offprice.features.checkout.presentation.screens

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
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
import com.apparel.offprice.features.checkout.data.AddressUiModel
import com.apparel.offprice.features.checkout.presentation.components.OrderSummarySection
import com.apparel.offprice.features.pdp.presentation.component.MoreBrandUI
import features.cart.presentation.component.PriceSummaryCard

@Composable
fun PaymentSuccessScreen(
    orderId: String,
    address: AddressUiModel?,
    onContinueShopping: () -> Unit,
    onMyOrders: () -> Unit,
    onViewAll: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item { Spacer(Modifier.height(32.dp)) }

        // ✅ SUCCESS ICON
        item {
            Icon(
                painter = painterResource(R.drawable.succes_tick_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = Color.Unspecified
            )
        }

        item { Spacer(Modifier.height(12.dp)) }

        item {
            Text(
                text = stringResource(R.string.label_order_placed_successfully),
                style = MaterialTheme.typography.titleMedium
            )
        }

        item { Spacer(Modifier.height(6.dp)) }

        item {
            Text(
                text = "Your order number is ${orderId}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        item {
            Text(
                text = stringResource(R.string.order_confirmation_email),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        item { Spacer(Modifier.height(24.dp)) }

        // ✅ TWO HORIZONTAL BUTTONS
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onContinueShopping,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        stringResource(R.string.continue_shopping),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                OutlinedButton(
                    onClick = onMyOrders,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        stringResource(R.string.label_my_orders),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item { Spacer(Modifier.height(32.dp)) }

        // ✅ ORDER SUMMARY
        item {
            OrderSummarySection()
        }

        item { Spacer(Modifier.height(24.dp)) }

        // ✅ DELIVERY DETAILS
        item {
            DeliveryDetailsSection(
                address = address ?: AddressUiModel(
                    name = "John Doe",
                    addressLine = "123 Main Street",
                    phone = "123-456-7890",
                    label = "Home",
                    id = "123456789"
                )
            )
        }

        item { Spacer(Modifier.height(24.dp)) }

        // ✅ PAYMENT METHOD
        item {
            PaymentMethodSummary("Credit Card")
        }

        item { Spacer(Modifier.height(24.dp)) }

        // ✅ PRICE SUMMARY (REUSED)
        item {
            PriceSummaryCard(
                isOpenShipFee = false,
                priceData = com.apparel.offprice.features.cart.data.priceData.apply {
                    isAutoCoupon = true
                },
                OnShipFeeClick = {}
            )
        }

        item {
            // ✅ YOU MAY ALSO LIKE
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                MoreBrandUI(stringResource(R.string.you_may_also_like),
                    onWishlistClick = {},
                    onProductClick = {})
            }
        }

        item { Spacer(Modifier.height(8.dp)) }

    }
}


@Composable
fun DeliveryDetailsSection(address: AddressUiModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E5E5)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = stringResource(R.string.label_delivery_details),
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(color = Color(0xFFE5E5E5))

            Spacer(Modifier.height(12.dp))

            Text(
                text = address.name,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = address.addressLine,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF555555)
            )
        }
    }
}


@Composable
fun PaymentMethodSummary(
    paymentText: String,
    isPaid: Boolean = true
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E5E5)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = stringResource(R.string.label_payment_method),
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(color = Color(0xFFE5E5E5))

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = paymentText,
                    style = MaterialTheme.typography.bodyMedium
                )

                if (isPaid) {
                    Text(
                        text = stringResource(R.string.label_paid),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

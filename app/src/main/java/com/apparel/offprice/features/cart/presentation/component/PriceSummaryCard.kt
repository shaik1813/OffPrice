package features.cart.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.IconBackgroundColor
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.greenColor
import com.apparel.offprice.features.cart.data.PriceData
import com.apparel.offprice.features.cart.presentation.component.PriceCardUI
import com.apparel.offprice.features.cart.presentation.component.ShippingFeeUI


@Composable
fun PriceSummaryCard(isOpenShipFee: Boolean, priceData: PriceData, OnShipFeeClick: () -> Unit) {

    Column() {
        Surface(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = IconBackgroundColor
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                PriceCardUI(
                    title = stringResource(R.string.subtotal)
                            + "(" + priceData.quantity + stringResource(R.string.items) + ")",
                    value = String.format("%.2f", priceData.subTotal)
                )

                PriceCardUI(
                    title = if(priceData.isAutoCoupon){
                        stringResource(R.string.discount)+" AUTO COUPON, COUPON"
                    } else stringResource(R.string.discount),
                    value = "-" + String.format("%.2f", priceData.discount),
                    valueColor = greenColor
                )

                PriceCardUI(
                    title = stringResource(R.string.shipping_fee),
                    value = "" + String.format("%.2f", priceData.shippingFee),
                    showArrow = true,
                    shipFeeClick = {
                        OnShipFeeClick()
                    }
                )

                if (isOpenShipFee) {
                    ShippingFeeUI(
                        stringResource(R.string.shipping_charges),

                        String.format("%.2f", 02.00), Color(0xFF8A8A8A)
                    )
                    ShippingFeeUI(
                        stringResource(R.string.international_shipping_charge),
                        String.format("%.2f", 02.00), Color(0xFF8A8A8A)
                    )
                    ShippingFeeUI(
                        stringResource(R.string.get_it_today),
                        String.format("%.2f", 10.00), Color(0xFF8A8A8A)
                    )
                }


                PriceCardUI(
                    title = stringResource(R.string.total_incl_vat),
                    value = "" + String.format("%.2f", priceData.total),
                    isBold = true
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                PriceCardUI(
                    title = stringResource(R.string.ca_points_used) + " - " + priceData.points + stringResource(
                        R.string.points
                    ),
                    value = ""+String.format("%.2f", priceData.caPointAmount),
                    valueColor = greenColor
                )

                PriceCardUI(
                    title = stringResource(R.string.store_credits_used),
                    value = "" + String.format("%.2f", priceData.storePointAmount),
                    valueColor = greenColor
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                PriceCardUI(
                    title = stringResource(R.string.grand_total),
                    value = "" + String.format("%.2f", priceData.grandTotal),
                    isBold = true
                )

            }
        }

        Spacer(modifier = Modifier.size(16.dp))
    }
}

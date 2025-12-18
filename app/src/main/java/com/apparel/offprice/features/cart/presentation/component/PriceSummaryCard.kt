package features.cart.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.features.cart.data.PriceData
import com.apparel.offprice.features.cart.presentation.component.PriceCardUI
import com.apparel.offprice.features.cart.presentation.component.ShippingFeeUI


@Composable
fun PriceSummaryCard(isOpenShipFee:Boolean, priceData : PriceData,OnShipFeeClick : () -> Unit) {

    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFF5F5F5)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            PriceCardUI(
                title = stringResource(R.string.subtotal)
                        +"("+priceData.quantity+ stringResource(R.string.items)+")",
                value = priceData.subTotal.toString()
            )

            PriceCardUI(
                title = stringResource(R.string.discount),
                value = "-"+priceData.discount,
                valueColor = Color(0xFF2E7D32)
            )

            PriceCardUI(
                title = stringResource(R.string.shipping_fee),
                value = ""+priceData.shipping_fee,
                showArrow = true,
                shipFeeClick = {
                   OnShipFeeClick()
                }
            )

            if(isOpenShipFee) {
                ShippingFeeUI(stringResource(R.string.shipping_charges),
                    "02.00",Color(0xFF8A8A8A))
                ShippingFeeUI(
                    stringResource(R.string.international_shipping_charge),
                    "02.00",Color(0xFF8A8A8A))
                ShippingFeeUI(
                    stringResource(R.string.get_it_today),
                    "10.00",Color(0xFF8A8A8A))
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            PriceCardUI(
                title = stringResource(R.string.total_incl_vat),
                value = ""+priceData.total,
                isBold = true
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            PriceCardUI(
                title = stringResource(R.string.ca_points_used) +" - "+priceData.points+ stringResource(
                    R.string.points
                ),
                value = "- "+priceData.caPointAmount,
                valueColor = Color(0xFF2E7D32)
            )

            PriceCardUI(
                title = stringResource(R.string.store_credits_used),
                value = "- "+priceData.storePointAmount,
                valueColor = Color(0xFF2E7D32)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            PriceCardUI(
                title = stringResource(R.string.grand_total),
                value = ""+priceData.grandTotal,
                isBold = true
            )
        }
    }
}

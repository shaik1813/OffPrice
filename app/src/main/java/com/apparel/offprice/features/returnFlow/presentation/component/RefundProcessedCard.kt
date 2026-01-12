package com.apparel.offprice.features.returnFlow.presentation.component

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.returnFlow.data.ReturnItem

@Composable
fun RefundProcessedCard(
    item: ReturnItem
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = surfaceColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Content column - no weight
            Column {
                // Title row (single line)
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.tick_success_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = stringResource(R.string.refund_processed_label),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1  // ensure single line
                    )
                }

                Spacer(Modifier.height(8.dp))


                val refundLabel = stringResource(R.string.refund_back_to_your_payment_method_label)


                // Subtitle (single line)
                Text(
                    text = "฿ ${item.price}$refundLabel",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    maxLines = 1
                )
            }

            // Spacer pushes visa to end
            Spacer(modifier = Modifier.weight(1f))

            // Visa icon at end, vertically centered
            Icon(
                painter = painterResource(id = R.drawable.card_visa),
                contentDescription = "Visa",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

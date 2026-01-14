package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.features.returnFlow.data.ReturnStatus

@Composable
fun ReturnStatusRow(status: ReturnStatus) {

    val (iconRes, text) = when (status) {
        ReturnStatus.REQUESTED -> R.drawable.green_approved_icon to "Return Requested"
        ReturnStatus.APPROVED -> R.drawable.green_approved_icon to "Approved"
        ReturnStatus.INITIATED -> R.drawable.yellow_initiated_icon to "Initiated"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.weight(1f)) // takes all remaining space

        Icon(
            painter = painterResource(R.drawable.icon_arrow_right),
            contentDescription = null
        )
    }

}
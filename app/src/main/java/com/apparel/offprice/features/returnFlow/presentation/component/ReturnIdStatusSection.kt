package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReturnIdStatusSection(
    returnId: String
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "RETURN ID - $returnId",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Requested On 22nd Oct, 2025",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.width(12.dp))

            StatusDotText(
                text = "Approved",
                color = Color(0xFF4CAF50)
            )
        }
    }
}

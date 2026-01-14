package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apparel.offprice.features.returnFlow.data.RefundMethod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefundMethodBottomSheet(
    selected: RefundMethod?,
    onSelect: (RefundMethod) -> Unit,
    onReturnItemClick: () -> Unit,
    onDismiss: () -> Unit  // add dismiss parameter
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = null,      // removes top center line
        containerColor = Color.White  // white background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Header with title + X button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Return Order",
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Choose Refund Method",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(6.dp))

            RefundOption(
                title = "Store Credits",
                description = "Instant Refund – Use credits immediately",
                selected = selected == RefundMethod.STORE_CREDITS,
                onClick = { onSelect(RefundMethod.STORE_CREDITS) }
            )

            RefundOption(
                title = "Original Payment Method",
                description = "5–7 Business Days – Refund to bank/card",
                selected = selected == RefundMethod.ORIGINAL_PAYMENT,
                onClick = { onSelect(RefundMethod.ORIGINAL_PAYMENT) }
            )

            Spacer(Modifier.height(16.dp))

            Button(
                enabled = selected != null,
                onClick = onReturnItemClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("RETURN ITEM (1)")
            }
        }
    }
}


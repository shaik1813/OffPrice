package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.features.returnFlow.data.ReturnReason
import com.apparel.offprice.features.returnFlow.data.displayText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnReasonBottomSheet(
    onSelect: (ReturnReason) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = null,  // removes top center line
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
                    text = stringResource(R.string.label_return_reason),
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

            ReturnReason.entries.forEach { reason ->
                ListItem(
                    headlineContent = {
                        Text(reason.displayText())
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier.clickable {
                        onSelect(reason)
                    }
                )
            }
        }
    }
}

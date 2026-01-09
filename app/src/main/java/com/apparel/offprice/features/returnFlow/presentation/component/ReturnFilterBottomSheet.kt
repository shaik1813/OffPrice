package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.returnFlow.data.ReturnFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnFilterBottomSheet(
    selectedFilter: ReturnFilter,
    onFilterSelected: (ReturnFilter) -> Unit,
    onSubmit: () -> Unit,
    onClose: () -> Unit = onSubmit
) {
    ModalBottomSheet(
        onDismissRequest = onClose,
        dragHandle = null,   // removes the top center line
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // Top header: FILTER + X
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.filter_label),
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Filters list
            ReturnFilter.entries.forEach { filter ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onFilterSelected(filter) }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = filter.toDisplayText(),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 14.sp
                    )

                    if (filter == selectedFilter) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Bottom bar style submit
            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.submit),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}


fun ReturnFilter.toDisplayText(): String =
    when (this) {
        ReturnFilter.LAST_1_MONTH -> "LAST 1 MONTH"
        ReturnFilter.LAST_2_MONTHS -> "LAST 2 MONTHS"
        ReturnFilter.LAST_3_MONTHS -> "LAST 3 MONTHS"
    }

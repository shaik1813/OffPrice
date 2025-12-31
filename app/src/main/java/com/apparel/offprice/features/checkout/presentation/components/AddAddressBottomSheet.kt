package com.apparel.offprice.features.checkout.presentation.components

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressBottomSheet(
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = null,
        containerColor = Color.White,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {

        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_add_new_address),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }

        HorizontalDivider()

        Spacer(Modifier.height(16.dp))

        // ADDRESS TYPE TOGGLE (REUSED)
        AddressTypeRow(
            selectedFilter = AddAddressFilter.Home,
            onFilterSelected = {}
        )

        Spacer(Modifier.height(16.dp))

        // FORM (simplified)
        AddressForm()

        Spacer(Modifier.height(80.dp))

        // FIXED BUTTON
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = onSave
        ) {
            Text("VERIFY & SAVE ADDRESS")
        }
    }
}

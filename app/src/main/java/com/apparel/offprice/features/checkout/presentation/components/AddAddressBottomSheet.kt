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
import androidx.compose.material3.Checkbox
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
import com.apparel.offprice.features.checkout.presentation.screens.CheckOutContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressBottomSheet(
    state: CheckOutContract.UiState,
    onDismiss: () -> Unit,
    onTypeSelected: (AddAddressFilter) -> Unit,
    onDefaultChecked: (Boolean) -> Unit,
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
                text =
                    if (state.addressSheetMode == AddressSheetMode.ADD)
                        stringResource(R.string.label_add_new_address_1)
                    else
                        stringResource(R.string.label_edit_address),
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
            selectedFilter = state.addAddressType,
            onFilterSelected = onTypeSelected
        )

        Spacer(Modifier.height(16.dp))

        // FORM (simplified)
        AddressForm(prefill = state.editingAddress)

        // MARK AS DEFAULT
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.markAsDefault,
                onCheckedChange = onDefaultChecked
            )
            Text(
                stringResource(R.string.label_mark_as_default_address),
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                onSave()
                /*event(AddAddressContract.UiEvent.OnSaveClicked)*/
            }
        ) {
            Text(
                text = stringResource(R.string.label_verify_save_address),
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}

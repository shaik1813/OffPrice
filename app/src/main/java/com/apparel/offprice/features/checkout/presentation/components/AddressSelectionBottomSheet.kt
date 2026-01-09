package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.checkout.data.AddressUiModel
import com.apparel.offprice.features.checkout.data.sampleAddresses
import com.apparel.offprice.features.checkout.presentation.screens.CheckOutContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressSelectionBottomSheet(
    selected: AddressUiModel?,
    onDismiss: () -> Unit,
    onAddressSelected: (AddressUiModel) -> Unit,
    onAddAddressClick: () -> Unit,
    event: (CheckOutContract.UiEvent) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        dragHandle = null,
        containerColor = Color.White
    ) {
        // HEADER WITH TITLE + CLOSE ICON
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.select_shipping_address),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }

        HorizontalDivider(
            Modifier.padding(horizontal = 16.dp),
            color = Color.LightGray
        )

        LazyColumn {
            itemsIndexed(sampleAddresses) { index, address ->
                AddressRow(
                    address = address,
                    selected = address.id == selected?.id,
                    onClick = { onAddressSelected(address) },
                    onEdit = { event(CheckOutContract.UiEvent.OnEditAddress(it)) }
                )

                if (index < sampleAddresses.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.LightGray
                    )
                }
            }
        }


        Spacer(Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                onAddAddressClick()
            }
        ) {
            Text(
                text = stringResource(R.string.add_address),
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}

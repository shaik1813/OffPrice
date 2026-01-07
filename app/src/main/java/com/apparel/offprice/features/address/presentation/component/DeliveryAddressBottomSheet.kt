package com.apparel.offprice.features.address.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.AppPhoneNumberField
import com.apparel.offprice.common.component.BottomSingleActionButton
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.secondaryColor
import com.apparel.offprice.features.address.data.model.DeliveryAddressFilter
import com.apparel.offprice.features.address.presentation.screen.DeliveryAddressContract
import com.apparel.offprice.features.address.presentation.screen.DeliveryAddressViewModel
import com.apparel.offprice.features.cart.presentation.component.CartCheckboxBox
import com.apparel.offprice.features.home.data.model.countryList
import com.apparel.offprice.features.profile.presentation.component.CategoryDropdown
import com.apparel.offprice.features.profile.presentation.component.LabeledField
import com.apparel.offprice.features.storeCredit.presentation.component.FilterChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryAddressBottomSheet(
    state: DeliveryAddressContract.AddressFormState,
    onEvent: (DeliveryAddressContract.UiEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetMaxWidth = BottomSheetDefaults.SheetPeekHeight,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.label_delivery_address).uppercase(),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            onDismiss()
                        }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            DeliveryAddressFilterRow(
                selectedFilter = state.tag,
                onFilterSelected = {
                    onEvent(DeliveryAddressContract.UiEvent.OnTagChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ---------------- Name ----------------
            LabeledField(
                label = stringResource(R.string.name),
                value = state.name,
                enabled = true,
                placeholder = stringResource(R.string.name),
                onValueChange = {
                   onEvent(DeliveryAddressContract.UiEvent.OnNameChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ---------------- Phone Number ----------------
            Text(
                text = stringResource(R.string.phone_no),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(6.dp))
            AppPhoneNumberField(
                value = state.phoneNumber,
                enabled = true,
                phoneCode = state.phoneCode,
                placeholder = stringResource(R.string.phone_no),
                onValueChange = {
                    onEvent(DeliveryAddressContract.UiEvent.OnPhoneChanged(it))
                },
                onCountrySelected = {
                    onEvent(DeliveryAddressContract.UiEvent.SelectCountry(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ---------------- Address ---------------------
            LabeledField(
                label = stringResource(R.string.label_address),
                value = state.address,
                enabled = true,
                placeholder = stringResource(R.string.label_address),
                onValueChange = {
                    onEvent(DeliveryAddressContract.UiEvent.OnAddressChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ---------------- City ---------------------
            CategoryDropdown(
                label = stringResource(R.string.label_city),
                categoriesList = listOf("Cairo", "Alexandria", "Giza", "Dubai", "Aswan", "Luxor"),
                selectedCategory = state.city,
                placeholder = stringResource(R.string.select_city),
                enabled = true,
                onCategorySelected = {
                    onEvent(DeliveryAddressContract.UiEvent.OnCityChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ---------------- Area ---------------------
            CategoryDropdown(
                label = stringResource(R.string.label_area),
                categoriesList = listOf("Cairo", "Alexandria", "Giza", "Dubai", "Aswan", "Luxor"),
                selectedCategory = state.area,
                placeholder = stringResource(R.string.label_select_area),
                enabled = true,
                onCategorySelected = {
                   onEvent(DeliveryAddressContract.UiEvent.OnAreaChanged(it))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                CartCheckboxBox(
                    checked = state.isDefault,
                    onCheckedChange = {
                        onEvent(DeliveryAddressContract.UiEvent.OnDefaultChanged(it))
                    },
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.label_default_address),
                    style = MaterialTheme.typography.bodySmall,
                    color = inputTextColor
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
        // Bottom Action Buttons
        BottomSingleActionButton(
            title = stringResource(R.string.label_verify_save_address),
            onButtonClicked = {
                onEvent(DeliveryAddressContract.UiEvent.OnSaveAddressClicked)
            }
        )
    }
}


@Composable
private fun DeliveryAddressFilterRow(
    selectedFilter: DeliveryAddressFilter,
    onFilterSelected: (DeliveryAddressFilter) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, buttonBorderColor.copy(alpha = 0.4f)),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.label_delivery_address_home),
                selected = selectedFilter == DeliveryAddressFilter.HOME,
                onClick = { onFilterSelected(DeliveryAddressFilter.HOME) },
            )
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.label_delivery_address_work),
                selected = selectedFilter == DeliveryAddressFilter.WORK,
                onClick = { onFilterSelected(DeliveryAddressFilter.WORK) },
            )
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.label_delivery_address_other),
                selected = selectedFilter == DeliveryAddressFilter.OTHER,
                onClick = { onFilterSelected(DeliveryAddressFilter.OTHER) },
            )
        }
    }
}
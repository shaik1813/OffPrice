package com.apparel.offprice.features.myorder.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.noRippleClickable
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.secondaryBlue
import com.apparel.offprice.features.myorder.data.OrderFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderFilterBottomSheet(
    selectedFilter: OrderFilter,
    onFilterSelected: (OrderFilter) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val filterOptions = listOf(
        OrderFilter.ALL to stringResource(R.string.all_orders).toUpperCase(Locale.current),
        OrderFilter.ORDER_RECEIVED to "DELIVERED",
        OrderFilter.INPROGRESS to "IN PROGRESS",
        OrderFilter.CANCELLED to "CANCELLED"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,

        sheetState = sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        dragHandle = null,
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetMaxWidth = BottomSheetDefaults.SheetPeekHeight
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.filter),
                    style = MaterialTheme.typography.titleMedium,
                )
                Icon(
                    painter = painterResource(R.drawable.close_login),
                    contentDescription = "Close",
                    tint = secondaryBlue,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onDismiss() }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            filterOptions.forEachIndexed { index, (filter, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .noRippleClickable {
                            onFilterSelected(filter)
                        },

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 14.sp,
                        color = primaryColor,
                        modifier = Modifier.weight(1f)
                    )

                    if (selectedFilter == filter) {
                        Icon(
                            painter = painterResource(R.drawable.tick_icon),
                            contentDescription = "Selected",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                if (index < filterOptions.size - 1) {
                    HorizontalDivider(thickness = 1.dp, color = Color(0xFFE5E5E5))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onDismiss() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_submit),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


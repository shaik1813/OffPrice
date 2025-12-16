package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.features.cart.data.couponItem
import com.apparel.offprice.features.cart.data.couponList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponOfferBottomSheet(
    sheetState: SheetState,
    onClose: () -> Unit,
    onApply: (String) -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        dragHandle = null,
        onDismissRequest = onClose,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        ) {

            // Header
            SheetHeader(onClose)

            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(1.dp)
                    .background(backgroundColor)
            )

            Spacer(modifier = Modifier.size(10.dp))

            // Coupon List
            LazyColumn() {
                items(couponList){ item ->
                    CouponCard(
                        title = item.title,
                        code = item.code,
                        description = item.desc,
                        onItemClick = { onApply(it) }
                    )

                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            BankCouponSection(couponItem)
        }
    }
}


@Composable
fun SheetHeader(onClose: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.coupon_code),
            style = MaterialTheme.typography.titleMedium
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            modifier = Modifier.clickable { onClose() }
        )
    }
}



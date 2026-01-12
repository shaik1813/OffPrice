package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.secondaryBlue
import com.apparel.offprice.features.coupon.data.sampleBankCouponList
import com.apparel.offprice.features.coupon.data.sampleCouponList
import com.apparel.offprice.features.coupon.presentation.screen.CouponContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponOfferBottomSheet(
    sheetState: SheetState,
    onClose: () -> Unit,
    onApply: (String) -> Unit,
    onOfferDetailClick: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        dragHandle = null,
        onDismissRequest = onClose,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
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

            CouponContent(
                coupons = sampleCouponList,
                bankCoupons = sampleBankCouponList,
                isCodeApplied = true,
                onApplyCoupon = { code ->
                    onApply(code)
                },
                onTermsAndConditionsClicked = {
                    onOfferDetailClick()
                },
                onCopyCode = {}
            )
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
            fontSize = 14.sp,
            color = primaryColor,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600))
        )


        Image(
            painter = painterResource(R.drawable.close_login),
            colorFilter = ColorFilter.tint(secondaryBlue),
            contentDescription = null,
            modifier = Modifier.size(22.dp).clickable { onClose() }
        )
    }
}



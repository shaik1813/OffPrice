package com.apparel.offprice.features.pdp.presentation.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.secondaryBlue
import com.apparel.offprice.features.pdp.data.model.TabbyPaymentInfo
import com.apparel.offprice.features.pdp.data.model.tabbyPaymentDetail


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabbyDetailSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    paymentInfo: TabbyPaymentInfo
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        ),
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(borderColor)
                .padding(horizontal = 16.dp, vertical = 18.dp)

        ) {

            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tabby_payicon),
                            contentDescription = null,
                            modifier = Modifier
                                .width(58.dp)
                                .height(23.dp)
                        )

                        Icon(
                            painter = painterResource(R.drawable.close_24px),
                            contentDescription = null,
                            tint = secondaryBlue,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { onDismiss() }
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(borderColor)
                    )

                    Spacer(modifier = Modifier.height(11.dp))

                    Image(
                        painter = painterResource(R.drawable.tabby_bgimg),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2.98f)
                    )
                }
            }

            itemsIndexed(tabbyPaymentDetail.paymentList) { index, item ->
                TabbyPaymentInfoCard(tabbyPaymentDetail.paymentList.get(index))
            }

            item {
                TabbyWorkDetailCard(tabbyData = tabbyPaymentDetail)

                TrustedByCard()

                ShopSafelyCard()

                paymentIcon()
            }
        }
    }
}


package com.apparel.offprice.features.pdp.presentation.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.secondaryBlue
import com.apparel.offprice.features.pdp.data.model.TamaraPaymentInfo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TamaraDetailSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    paymentInfo: TamaraPaymentInfo
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
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tamara_payicon),
                            contentDescription = null,
                            modifier = Modifier
                                .width(68.dp)
                                .height(21.dp)
                                .align(Alignment.Center)
                        )

                        Icon(
                            painter = painterResource(R.drawable.close_24px),
                            contentDescription = null,
                            tint = secondaryBlue,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { onDismiss() }
                                .align(Alignment.CenterEnd)
                        )
                    }

                    Spacer(modifier = Modifier.size(6.dp))

                    Text(
                        text = "Your payment, your peace",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 18.sp,
                        color = primaryColor
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    Text(
                        text = "Example plans",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        color = primaryColor
                    )

                }
            }

            itemsIndexed(paymentInfo.paymentList) { index, item ->
                TamaraPayCard(paymentInfo.paymentList.get(index))
            }

            item {
                TamaraWorkDetailCard(tamaraData = paymentInfo)

                WhyTamaraDetailCard()
            }
        }
    }
}


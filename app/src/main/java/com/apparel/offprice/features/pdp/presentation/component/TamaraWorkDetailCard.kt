package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.common.theme.sizeCardColor
import com.apparel.offprice.features.pdp.data.model.TamaraPaymentInfo


@Composable
fun TamaraWorkDetailCard(tamaraData: TamaraPaymentInfo) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "How it Works",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                fontSize = 15.sp,
                color = saleCardColor
            )

            Spacer(modifier = Modifier.size(4.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                tamaraData.workDesc.forEachIndexed { index, item ->

                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(sizeCardColor)
                        ) {
                            Text(
                                text = (index + 1).toString(),
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 10.sp,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Spacer(modifier = Modifier.size(6.dp))

                        Column{
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(600)),
                                fontSize = 14.sp,
                                color = primaryColor,
                            )

                            Spacer(modifier = Modifier.size(4.dp))

                            Text(
                                text = item.desc,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 12.sp,
                                color = nonreturnTxtColor,
                            )
                        }
                                            }
                }

            }
        }

    }
}
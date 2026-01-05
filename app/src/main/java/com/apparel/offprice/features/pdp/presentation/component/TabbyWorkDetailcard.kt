package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.common.theme.sizeCardColor
import com.apparel.offprice.features.pdp.data.model.TabbyPaymentInfo


@Composable
fun TabbyWorkDetailCard(tabbyData: TabbyPaymentInfo) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "How it Works",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 14.sp,
                color = saleCardColor
            )

            Spacer(modifier = Modifier.height(7.dp))

            LazyColumn {
                itemsIndexed(tabbyData.workDesc) { index, item ->

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(16.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(sizeCardColor)
                        ) {
                            Text(
                                text = "$index",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.size(6.dp))

                        Text(
                            text = "${tabbyData.workDesc.get(index)}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 10.sp,
                            color = Color(0xFF36393)
                        )
                    }
                }
            }


        }
    }
}
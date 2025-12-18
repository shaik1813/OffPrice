package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.features.cart.data.offerList


@Composable
fun OfferDetailsDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.offer_details),
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(14.dp)
                            .clickable { onDismiss() },
                        tint = Color(0xFF333333)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(color = Color(0xFFE6E6E6), modifier = Modifier.height(1.dp))

                Spacer(modifier = Modifier.height(14.dp))

                // Section Title
                Text(
                    text = stringResource(R.string.terms_and_conditions),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn() {
                    items(offerList){
                        OfferBullet(it)
                    }

                }

            }
        }
    }
}


@Composable
fun OfferBullet(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "•",
            fontSize = 12.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = inputTextColor
        )
    }
}


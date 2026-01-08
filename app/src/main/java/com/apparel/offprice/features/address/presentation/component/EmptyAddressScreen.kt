package com.apparel.offprice.features.address.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.nonreturnTxtColor


@Composable
fun EmptyAddressScreen(
    onAddAddressClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon_empty_address),
            contentDescription = "Address",
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.label_no_address_saved),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.text_no_address_description),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            color = nonreturnTxtColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            onClick = { onAddAddressClicked() },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.label_add_new_address).uppercase(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }
    }
}
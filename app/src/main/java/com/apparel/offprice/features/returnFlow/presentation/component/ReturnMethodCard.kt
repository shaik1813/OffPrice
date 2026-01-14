package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.returnFlow.data.ReturnMethod
import com.apparel.offprice.features.returnFlow.data.description
import com.apparel.offprice.features.returnFlow.data.title

@Composable
fun ReturnMethodCard(
    method: ReturnMethod,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            if (selected) Color.Black else surfaceColor
        ),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            RadioButton(
                selected = selected,
                onClick = null
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = method.title(),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = method.description(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = inputTextColor
                )
            }
        }
    }
}

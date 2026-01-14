package com.apparel.offprice.features.returnFlow.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.returnFlow.data.ReturnFilter

@Composable
fun ReturnsHeaderRow(
    selectedFilter: ReturnFilter,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.label_completed).uppercase(),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        OutlinedButton(
            onClick = onFilterClick,
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp)
        ) {
            Text(
                text = selectedFilter.toDisplayText().replace("LAST ", ""),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.arrow_down_black_icon),
                contentDescription = null
            )
        }
    }
}

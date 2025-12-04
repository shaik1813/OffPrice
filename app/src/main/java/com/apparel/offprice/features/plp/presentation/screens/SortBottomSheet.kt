package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun SortBottomSheet(
    onClose: () -> Unit,
    onSortSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {

        // HEADER ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.recommended_label),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onClose) {
                Icon(
                    painter = painterResource(R.drawable.close_24px),
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // SORT OPTIONS
        SortOptionItem("Price: High to Low") { onSortSelected("high_to_low") }
        SortOptionItem("Price: Low to High") { onSortSelected("low_to_high") }
        SortOptionItem("New Arrivals") { onSortSelected("new_arrivals") }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun SortOptionItem(
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
    }
}


@Preview(showBackground = true)
@Composable
fun SortBottomSheetPreview() {
    SortBottomSheet(onClose = {}, onSortSelected = {})
}
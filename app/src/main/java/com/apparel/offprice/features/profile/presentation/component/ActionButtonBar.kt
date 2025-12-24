package com.apparel.offprice.features.profile.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionButtonsBar(
    modifier: Modifier = Modifier,
    leftButtonText: String = "CANCEL",
    rightButtonText: String = "OK",
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = onLeftClick,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                ),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = leftButtonText,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp
                ),
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Button(
            onClick = onRightClick,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = rightButtonText,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp
                )
            )
        }
    }
}
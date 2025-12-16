package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun CartCheckboxBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clickable { onCheckedChange(!checked) }
    ) {
        Image(
            painter = painterResource(
                id = if (checked)
                    R.drawable.checked
                else
                    R.drawable.uncheck
            ),
            contentDescription = null
        )
    }
}

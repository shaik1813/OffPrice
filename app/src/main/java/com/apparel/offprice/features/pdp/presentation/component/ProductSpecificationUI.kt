package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.loginButtonColor

@Composable
fun ProductSpecificationUI() {

    val specs = listOf(
        "Style" to "Casual",
        "Country Of Origin" to "India",
        "Model Name" to "7003830901Black",
        "Colour" to "Black",
        "Gender" to "Male"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        specs.forEachIndexed { index, item ->

            val background = if (index % 2 == 1) Color(0xFFF5F5F5) else Color.White

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = item.first,
                    fontSize = 11.sp,
                    color = Color(0xFF545454),
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = item.second,
                    fontSize = 14.sp,
                    color = loginButtonColor,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

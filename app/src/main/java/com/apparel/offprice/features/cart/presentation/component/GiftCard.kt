package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor


@Composable
fun GiftCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Title
            Text(
                text = stringResource(R.string.gift_card),
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            var cardNo by remember { mutableStateOf("") }
            var pinNo by remember { mutableStateOf("") }

            // Input fields row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .clickable {}
                        .clip(RoundedCornerShape(6.dp))
                        .border(                             // 2️⃣ then border
                            width = 1.dp,
                            color = backgroundColor,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(17.dp)
                ) {
                    if (cardNo.isEmpty()) {
                        Text(
                            text = "Enter 25-Digit Card Number",
                            fontSize = 12.sp,
                            color = Color(0xFF8C8D8D)
                        )
                    }

                    BasicTextField(
                        value = cardNo,
                        onValueChange = { cardNo = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            color = inputTextColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        cursorBrush = SolidColor(inputTextColor)
                    )
                }

                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .wrapContentHeight()
                        .clickable {}
                        .clip(RoundedCornerShape(6.dp))
                        .border(                             // 2️⃣ then border
                            width = 1.dp,
                            color = backgroundColor,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(17.dp)
                ) {

                    if (pinNo.isEmpty()) {
                        Text(
                            text = stringResource(com.apparel.offprice.R.string.pin),
                            fontSize = 12.sp,
                            color = Color(0xFF8C8D8D)
                        )
                    }


                    BasicTextField(
                        value = pinNo,
                        onValueChange = { pinNo = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 12.sp,
                            color = inputTextColor
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        cursorBrush = SolidColor(inputTextColor)
                    )
                }


            }

            Spacer(modifier = Modifier.height(16.dp))

            // Apply button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {}
                    .clip(RoundedCornerShape(8.dp))
                    .border(                            // 2️⃣ then border
                        width = 1.dp,
                        color = Color(0xFF040707),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    text = stringResource(com.apparel.offprice.R.string.apply),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(15.dp)
                )
            }
        }
    }
}

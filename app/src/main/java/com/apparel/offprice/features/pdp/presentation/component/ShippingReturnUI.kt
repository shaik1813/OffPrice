package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor

@Composable
fun ReturnExchangeSection() {

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            InfoCard(
                Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                title = stringResource(com.apparel.offprice.R.string.returnable),
                subText = "Hassle Free Return\nWithin 20 Days",
                icon = Icons.Default.Undo
            )

            InfoCard(
                Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                title = stringResource(com.apparel.offprice.R.string.exchangeable),
                subText = "Place An Exchange\nWithin 14 Days",
                icon = Icons.Default.Autorenew
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF545454),
                    fontWeight = FontWeight.Normal, fontSize = 10.sp)){
                    append(stringResource(com.apparel.offprice.R.string.learn_more_return))
                }
                withStyle(
                    style = SpanStyle(
                        color = loginButtonColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                ) {
                    append(stringResource(com.apparel.offprice.R.string.here))
                }
            },
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}


@Composable
fun InfoCard(modifier: Modifier, title: String, subText: String, icon: ImageVector) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, color = Color(0xFFF3F3F3)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp),
            )

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = title,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleMedium,
                    color = loginButtonColor

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subText,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = inputTextColor
                )
            }
        }

    }
}

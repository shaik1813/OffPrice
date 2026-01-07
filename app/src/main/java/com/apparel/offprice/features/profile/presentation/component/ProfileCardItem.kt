package com.apparel.offprice.features.profile.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.nonreturnTxtColor

@Composable
fun ProfileCardItem(
    icon: Int,
    userItem: String,
    userDescription: String,
    onItemClicked: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, color = buttonBorderColor),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "User Profile Icon",
                    tint = loginButtonColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = userItem.uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp
                        ),
                        color = Color.Black
                    )
                    Text(
                        text = userDescription,
                        style = MaterialTheme.typography.labelSmall,
                        color = nonreturnTxtColor
                    )
                }
            }
            Icon(
                painter = painterResource(R.drawable.icon_arrow_right),
                contentDescription = "Arrow Right"
            )
        }
    }
}
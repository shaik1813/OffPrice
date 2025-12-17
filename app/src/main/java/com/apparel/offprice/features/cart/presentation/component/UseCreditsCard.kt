package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.authentication.presentation.component.CustomCheckbox
import com.apparel.offprice.features.cart.data.Creditsdata


@Composable
fun UseCreditsCard(
    creditsdata: Creditsdata,
    caPointsChecked: Boolean,
    storeCreditsChecked: Boolean,
    onCaPointsToggle: (Boolean) -> Unit,
    onStoreCreditsToggle: (Boolean) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = stringResource(R.string.use_credits),
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            CreditRow(
                checked = caPointsChecked,
                title = stringResource(R.string.ca_points),
                points = creditsdata.clubApparelpoint.points,
                amount = creditsdata.clubApparelpoint.amount,
                painterResource = painterResource(R.drawable.ca_crediticon),
                onCheckedChange = onCaPointsToggle
            )

            Spacer(modifier = Modifier.height(12.dp))

            CreditRow(
                checked = storeCreditsChecked,
                title = stringResource(R.string.store_credits),
                points = "",
                amount = creditsdata.storePoint.amount,
                painterResource = painterResource(R.drawable.store_crediticon),
                onCheckedChange = onStoreCreditsToggle
            )
        }
    }
}



@Composable
fun CreditRow(
    checked: Boolean,
    title: String,
    points: String,
    amount: String,
    painterResource : Painter,
    onCheckedChange: (Boolean) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .clickable(
                    indication = null,
                    interactionSource = null
                ) { onCheckedChange(!checked) },
            verticalAlignment = Alignment.CenterVertically
        ) {

            CartCheckboxBox(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                modifier = Modifier.size(10.dp)
            )

            Image(
                painter = painterResource,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 13.dp)
                    .size(17.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 12.sp
                    )

                    if (points.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = points,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF8A8A8A)
                        )
                    }
                }
            }

            Text(
                text = amount,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 12.sp,
                color = Color(0xFF4CAF50)
            )
        }

    }
}

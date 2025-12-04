package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.offerCardColor
import com.apparel.offprice.features.pdp.presentation.component.dottedBorder

@Composable
fun OfferCardUI() {

    var click by remember{ mutableStateOf(false) }

    var selectedCity by remember { mutableStateOf("") }


    if(click) LocationSheetPDP(selectedCity, onCitySelected = { selectedCity = it}, onDismiss = {})

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .graphicsLayer(alpha = 1f)
            .dottedBorder(
                color = Color(0xFFE2B2B4),
                strokeWidth = 1.dp,
                cornerRadius = 10.dp,
                dashLength = 8.dp,
                gapLength = 6.dp
            )
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(offerCardColor, shape = RoundedCornerShape(8.dp))
                .padding(vertical = 12.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Christmas Offer",
                color = Color(0xFFEFEFEF),
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.width(10.dp))

            HorizontalDivider(
                modifier = Modifier
                    .height(20.dp)
                    .width(1.dp)
                    .background(Color(0xFFD48C90))
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "30% off",
                color = Color.White,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(com.apparel.offprice.R.string.see_all),
                color = Color(0xFFEFEFEF),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ----- Delivery Time Tag -----
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFFEEFCB), shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 5.dp, vertical = 1.dp)
            ) {

                Row(horizontalArrangement = Arrangement.Center) {
                    Image(
                        painter = painterResource(R.drawable.arrive_icon),
                        contentDescription = "arrive"
                    )

                    Text(
                        text = "ARRIVING IN 2-3 DAYS",
                        fontSize = 7.sp,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black
                    )
                }
            }


            Spacer(modifier = Modifier.height(5.dp))

            // ----- Location -----
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth().clickable{
                    click = true
                }
            ) {

                Icon(
                    painter = painterResource(R.drawable.location_icon),
                    contentDescription = null,
                )

                Text(
                    text = "Abu Dhabi Gold club-Abu",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF141414)
                )

                Icon(
                    painter = painterResource(R.drawable.down_arrow),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

        }


    }
}

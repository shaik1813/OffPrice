package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.blueRoundColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.sizeCardColor


@Preview
@Composable
fun ColorSection() {
    Column(modifier = Modifier.padding(top = 16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.selected_color),
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium,
                color = loginButtonColor
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(blueRoundColor)
            )
            Text(
                text = "Blue",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 5.dp),
                style = MaterialTheme.typography.titleMedium,
                color = loginButtonColor
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Row() {
            repeat(4) { it ->
                Card(
                    shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(0.dp),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .background(sizeCardColor)
                        .width(50.dp)
                        .height(59.dp)
                )
                {

                    var sourceId: Int = 0

                    if (it % 2 == 0)
                        sourceId = R.drawable.colorimg
                    else sourceId = R.drawable.colorimg2


                    Image(
                        painter = painterResource(sourceId),
                        contentDescription = "colorIcon",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }



    }
}
package com.apparel.offprice.features.pdp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.nonReturnbgColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.common.theme.saleEndTextColor


@Preview(showBackground = true)
@Composable
fun SalesEndSection() {
    Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically ) {
        Text(
            text = "Sales Ends in",
            style = MaterialTheme.typography.bodyMedium,
            color = saleEndTextColor,
            textAlign = TextAlign.Center
        )

        Card(
            shape = RoundedCornerShape(2.45.dp),
            colors = CardDefaults.cardColors(
                containerColor = saleCardColor
            ),
            modifier = Modifier.padding(start = 10.dp, top=6.dp,bottom = 6.dp, end = 6.dp).align(Alignment.CenterVertically)
        ) {
            Column(modifier = Modifier.padding(vertical = 2.3.dp, horizontal = 6.dp)) {
                Text(
                    text = "01",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.size(1.dp))

                HorizontalDivider(modifier = Modifier
                    .width(7.48.dp)
                    .height(0.63.dp))

                Spacer(modifier = Modifier.size(1.dp))

                Text(
                    text = "Day",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontSize = 7.sp
                )
            }
        }



        Card(
            shape = RoundedCornerShape(2.45.dp),
            colors = CardDefaults.cardColors(
                containerColor = saleCardColor
            ),
            modifier = Modifier.padding(start = 10.dp, top=6.dp,end = 6.dp).align(Alignment.CenterVertically)
        ) {
            Column(modifier = Modifier.padding(vertical = 2.3.dp, horizontal = 6.dp).align(Alignment.CenterHorizontally)) {
                Text(
                    text = "15",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.size(1.dp))

                HorizontalDivider(modifier = Modifier
                    .width(7.48.dp)
                    .height(0.63.dp))

                Spacer(modifier = Modifier.size(1.dp))

                Text(
                    text = "Hours",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontSize = 7.sp
                )
            }
        }



        Card(
            shape = RoundedCornerShape(2.45.dp),
            colors = CardDefaults.cardColors(
                containerColor = saleCardColor
            ),
            modifier = Modifier.padding(start = 10.dp, top=6.dp,end = 6.dp).align(Alignment.CenterVertically)
        ) {
            Column(modifier = Modifier.padding(vertical = 2.3.dp, horizontal = 6.dp).align(Alignment.CenterHorizontally)) {
                Text(
                    text = "29",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.size(1.dp))

                HorizontalDivider(modifier = Modifier
                    .width(7.48.dp)
                    .height(0.63.dp))

                Spacer(modifier = Modifier.size(1.dp))

                Text(
                    text = "Mins",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontSize = 7.sp
                )
            }
        }




        Card(
            shape = RoundedCornerShape(2.45.dp),
            colors = CardDefaults.cardColors(
                containerColor = saleCardColor
            ),
            modifier = Modifier.padding(start = 10.dp, top=6.dp,end = 6.dp).align(Alignment.CenterVertically)
        ) {
            Column(modifier = Modifier.padding(vertical = 2.3.dp, horizontal = 6.dp).align(Alignment.CenterHorizontally)) {
                Text(
                    text = "58",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.size(1.dp))

                HorizontalDivider(modifier = Modifier
                    .width(7.48.dp)
                    .height(0.63.dp))

                Spacer(modifier = Modifier.size(1.dp))

                Text(
                    text = "Mins",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontSize = 7.sp
                )
            }
        }

    }
}

@Composable
fun salesEnd(){
    Row(verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = "Sales Ends in",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFB30000), // Red Text
            modifier = Modifier.padding(end = 12.dp)
        )

        TimeBox(value = 1, label = "Day")
        TimeBox(value = 5, label = "Hours")
        TimeBox(value = 20, label = "Mins")
        TimeBox(value = 30, label = "Sec")
    }
}



@Composable
fun TimeBox(
    value: Long,
    label: String
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        modifier = Modifier.padding(horizontal = 3.dp, vertical = 6.5.dp)
    ) {
        Column(
            modifier = Modifier
                .width(25.dp)
                .padding(vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = String.format("%02d", value),
                color = Color.White,
                fontSize = 11.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
package com.apparel.offprice.features.welcome.presentation.location

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.features.home.data.model.Country

@Composable
fun ChooseLocationCard(
    item: Country,
    onClick: (Country) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick(item) },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFE0E0E0)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(item.countryFlag),
                contentDescription = item.countryName,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = item.countryName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(0.5f),
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = item.countryNameArabic,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.End,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun CategoryCardPreview(){
    ChooseLocationCard(
        item = Country(
            countryName = "UAE",
            countryCode = "+971",
            countryFlagRound = R.drawable.icon_flag_uae_round,
            countryFlag = R.drawable.icon_flag_uae,
            countryNameArabic = "الإمارات"
        ),
        onClick = {}
    )
}
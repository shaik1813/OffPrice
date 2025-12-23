package com.apparel.offprice.features.welcome.presentation.component

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.features.home.data.model.Country

@Composable
fun RegionSelectionCard(
    item: Country,
    onClick: (Country) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick(item) },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(item.countryFlag),
                contentDescription = item.countryName,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = item.countryName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp
                ),
                modifier = Modifier.weight(0.5f),
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = item.countryNameArabic,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(0.5f),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun CategoryCardPreview(){
    RegionSelectionCard(
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
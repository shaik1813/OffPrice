package com.apparel.offprice.features.storeLocator.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.features.storeLocator.data.CountryStoreList


@Composable
fun StoreCountryList(
    countryList: List<CountryStoreList>,
    selectedCountry: CountryStoreList,
    onCountrySelected: (CountryStoreList) -> Unit
){
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        items(countryList) { tab ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (selectedCountry == tab) MaterialTheme.colorScheme.secondary else borderColor
                    )
                    .clickable {
                        onCountrySelected(tab)
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "${tab.country} (${tab.storeList.size})".uppercase(),
                    color = if (selectedCountry == tab) Color.White else Color.Black,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 12.sp
                )
            }
        }
    }
}
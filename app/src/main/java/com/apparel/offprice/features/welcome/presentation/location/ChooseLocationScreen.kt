package com.apparel.offprice.features.welcome.presentation.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.features.welcome.data.model.LocationSelectionItem
import com.apparel.offprice.features.welcome.data.model.sampleLocationSelectionItems

@Composable
fun ChooseLocationScreen(
    onItemClick: (LocationSelectionItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // Title
        Text(
            text = "OFF/PRICE",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Subtitle
        Text(
            text = "CHOOSE YOUR LOCATION",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Location List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(sampleLocationSelectionItems) { item ->
                ChooseLocationCard(item){
                    onItemClick(item)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChooseLocationPreview(){
    ChooseLocationScreen(onItemClick = {})
}
package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apparel.offprice.features.plp.data.model.PLPHorizontalListItem
import com.apparel.offprice.features.plp.data.model.samplePLPHorizontalListItems

@Composable
fun PLPCategoryHorizontalList(
    categories: List<PLPHorizontalListItem>,
    selectedCategoryId: String,
    onCategoryClick: (PLPHorizontalListItem) -> Unit
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {

        items(categories) { item ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onCategoryClick(item) }
            ) {

                // Grey rounded icon box
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEAEAEA)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(item.img),
                        contentDescription = item.title,
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Category Title (bold + underline when selected)
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = if (item.id == selectedCategoryId) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                // Underline only for selected item
                /*if (item.id == selectedCategoryId) {
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(30.dp)
                            .background(Color.Black)
                            .padding(top = 2.dp)
                    )
                }*/
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun PLPCategoryHorizontalListPreview() {

    var selectedCategoryId by remember { mutableStateOf("1") }

    PLPCategoryHorizontalList(
        categories = samplePLPHorizontalListItems,
        selectedCategoryId = selectedCategoryId,
        onCategoryClick = { item ->
            selectedCategoryId = item.id
        }
    )
}

package com.apparel.offprice.features.home.presentation.screens.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.apparel.offprice.features.home.data.model.CategoryItem

@Composable
fun CategoryCard(
    item: CategoryItem,
    onClick: (CategoryItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick(item) },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White     // ✔ Pure white
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFE0E0E0)        // ✔ Light grey outline (#E0E0E0)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(item.title, style = MaterialTheme.typography.titleSmall)
            }

            AsyncImage(
                model = item.img,
                contentDescription = item.title,
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun categoryCardPreview(){
    CategoryCard(
        item = CategoryItem("1", "Category 1", "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA="),
        onClick = {}
    )
}
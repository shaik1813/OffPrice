package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.apparel.offprice.common.theme.IconBackgroundColor
import com.apparel.offprice.features.welcome.data.model.GenderCategoryItem

@Composable
fun GenderCategoryCard(
    item: GenderCategoryItem,
    onClick: (GenderCategoryItem) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(IconBackgroundColor, shape = RoundedCornerShape(16.dp)) // Background with rounded corners
            .padding(10.dp) // Padding space visible as buttonBorderColor
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(item) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = item.img,  // String URL
                contentDescription = item.label,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay bottom for text visibility
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = item.label,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
}

@Preview(showBackground = true)
@Composable
fun GenderCategoryCardPreview() {
    GenderCategoryCard(
        item = GenderCategoryItem("1", "Men", "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww"),
        onClick = {})
}


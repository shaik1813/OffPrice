package com.apparel.offprice.features.welcome.presentation.genderCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.apparel.offprice.R
import com.apparel.offprice.features.welcome.data.model.GenderCategoryItem

@Composable
fun GenderCategoryCard(
    item: GenderCategoryItem,
    onClick: (GenderCategoryItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .clickable { onClick(item) },
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            item.backgroundColor.first,
                            item.backgroundColor.second
                        )
                    )
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = item.image)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = item.label,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth()

            )
            // Gradient overlay bottom for text visibility
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = item.label,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 30.sp
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenderCategoryCardPreview() {
    GenderCategoryCard(
        item = GenderCategoryItem(
            id = "1",
            label = "MEN",
            image = R.drawable.image_men
        ),
        onClick = {})
}


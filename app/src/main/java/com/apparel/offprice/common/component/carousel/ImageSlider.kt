package com.apparel.offprice.common.component.carousel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.apparel.offprice.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderWithIndicatorPLP(
    images: List<Int>,
    selectedIndicatorColor: Color = Color.White,
    unSelectedIndicatorColor: Color = Color.LightGray,
    modifier: Modifier = Modifier,
    placeholder: Int = R.drawable.icon_empty_product
) {
    val safeImages = images.ifEmpty { listOf(placeholder) }
    val pagerState = rememberPagerState(pageCount = { safeImages.size })
    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(safeImages[page])
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .error(placeholder)
                        .placeholder(placeholder)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }
        }
        if (safeImages.size > 1 && images.isNotEmpty()) { //shows only when there are more than one Images
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .background(
                            color = Color.Transparent.copy(0.2f),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    images.forEachIndexed { index, _ ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) selectedIndicatorColor else unSelectedIndicatorColor
                                )
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderWithIndicatorPDP(
    images: List<String>,
    selectedIndicatorColor: Color = Color.DarkGray,
    unSelectedIndicatorColor: Color = Color.LightGray,
    modifier: Modifier = Modifier
) {
    if (images.isEmpty()) {
        return
    }
    val pagerState = rememberPagerState(pageCount = { images.size })

    val configuration = LocalConfiguration.current

    // This will only recalculate if configuration changes (rotation, screen change)
    val screenHeight = remember(configuration) {
        configuration.screenHeightDp.dp / 2
    }
//    BoxWithConstraints(modifier = modifier.height(screenHeight)) {
    Box() {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight)
        ) { page ->

            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .placeholder(R.drawable.icon_empty_product)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = screenHeight * 0.02f)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                val currentPage by remember { derivedStateOf { pagerState.currentPage } }

                images.forEachIndexed { index, _ ->
                    val isSelected = currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .size(if (isSelected) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) selectedIndicatorColor else unSelectedIndicatorColor
                            )
                    )
                }
            }
        }
    }
}

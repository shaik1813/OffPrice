package com.apparel.offprice.common.component.carousel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.apparel.offprice.common.theme.OffPriceTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderWithIndicator(
    images: List<String>,
    selectedIndicatorColor: Color = Color.DarkGray,
    unSelectedIndicatorColor: Color = Color.LightGray,
    modifier: Modifier = Modifier
) {
    if (images.isEmpty()) {
        return
    }
    val pagerState = rememberPagerState(pageCount = { images.size })
    BoxWithConstraints(modifier = modifier) {
        val screenHeight = this.maxHeight
        val imageSize = screenHeight * 0.45f
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageSize)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageSize)
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
                images.forEachIndexed { index, _ ->
                    val isSelected = pagerState.currentPage == index
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
    BoxWithConstraints(modifier = modifier) {
        val screenHeight = this.maxHeight
        val imageSize = screenHeight * 0.45f
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageSize)
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(images[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageSize)
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
                images.forEachIndexed { index, _ ->
                    val isSelected = pagerState.currentPage == index
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


@Preview(showBackground = true)
@Composable
fun ImageSliderDemo() {
    val images = listOf(
        "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww",
        "https://plus.unsplash.com/premium_photo-1690303193898-f9c721d0770b?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fHVybHxlbnwwfHwwfHx8MA%3D%3D",
        "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA=",
        "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="
    )
    OffPriceTheme {
        ImageSliderWithIndicator(images)
    }
}

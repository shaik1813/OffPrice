package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import com.apparel.offprice.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderForRowPLP(
    images: List<Int>,
    modifier: Modifier = Modifier,
    placeholder: Int = R.drawable.icon_empty_product
) {
    val safeImages = images.ifEmpty { listOf(placeholder) }
    val pagerState = rememberPagerState(pageCount = { 1 })
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(safeImages[0])
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
}


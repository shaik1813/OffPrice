package com.apparel.offprice.features.customerSupport.presentation.screens.aboutUs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBarWithAction
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.customerSupport.data.Brand
import com.apparel.offprice.features.customerSupport.presentation.component.HtmlText

@Composable
fun AboutUsScreen(
    onNavigateToBack: () -> Unit,
    onSearchClicked: () -> Unit,
    onWishlistClicked: () -> Unit,
    viewModel: AboutUsViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBarWithAction(
                title = stringResource(R.string.label_about_us),
                onBackPressed = { onNavigateToBack() },
                onSearchClicked = { onSearchClicked() },
                onWishlistClicked = { onWishlistClicked() }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Hero Image with people on couch
            HeroImageSection()

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.label_about_off_price),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description Text
            HtmlText(state.aboutUsText)

            Spacer(modifier = Modifier.height(24.dp))


            // Feature Cards Grid
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = 16.dp, bottom = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_off_price_experience),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        FeatureCard(
                            icon = state.featureCards[0].imageIcon,
                            title = state.featureCards[0].title,
                            description = state.featureCards[0].description,
                            modifier = Modifier.weight(1f),
                        )
                        FeatureCard(
                            icon = state.featureCards[1].imageIcon,
                            title = state.featureCards[1].title,
                            description = state.featureCards[1].description,
                            modifier = Modifier.weight(1f),
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        FeatureCard(
                            icon = state.featureCards[2].imageIcon,
                            title = state.featureCards[2].title,
                            description = state.featureCards[2].description,
                            modifier = Modifier.weight(1f),
                        )
                        FeatureCard(
                            icon = state.featureCards[3].imageIcon,
                            title = state.featureCards[3].title,
                            description = state.featureCards[3].description,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }

            // Brands We Love Section with Horizontal Scroll
            BrandsWeLoveSection(brands = state.brands)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun HeroImageSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(Color(0xFFF5F5F5))
    ) {
        Image(
            painter = painterResource(id = R.drawable.about_us_group),
            contentDescription = "About Us Hero",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun FeatureCard(
    icon: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = nonreturnTxtColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun BrandsWeLoveSection(brands: List<Brand>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.label_brand_we_love),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Start
        )


        // Horizontal Scrollable Brand List
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(brands) { brand ->
                BrandLogoItem(brand = brand)
            }
        }
    }
}

@Composable
private fun BrandLogoItem(brand: Brand) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(150.dp)
    ) {
        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = brand.imageRes),
                contentDescription = brand.name,
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = brand.name,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}


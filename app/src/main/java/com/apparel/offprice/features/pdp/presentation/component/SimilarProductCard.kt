package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.carousel.ImageSliderWithIndicatorPLP
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.common.utils.toComposeColorSafe
import com.apparel.offprice.features.plp.data.model.ProductCardItems


@Composable
fun SimilarProductCard(
    heightOfList: Dp,
    product: ProductCardItems,
    onWishlistClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width((heightOfList / 2))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((heightOfList / 2) * 1.27f)
                // Height = Width * 0.75  (change based on Figma)
                .clip(shape = MaterialTheme.shapes.small)
                .background(brush = productCardColor)
        ) {
            ImageSliderWithIndicatorPLP(
                images = product.image,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 6.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(
                        containerColor = product.tagContainerColor.toComposeColorSafe(),
                        contentColor = product.tagContentColor.toComposeColorSafe()
                    )
                ) {
                    Text(
                        text = product.tag?.uppercase() ?: "",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier
                            .padding(all = 4.dp)
                    )
                }
                Image(
                    painter = painterResource(
                        if (product.isWishlist) R.drawable.heart_red_icon
                        else R.drawable.heart_icon
                    ),
                    contentDescription = product.title,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(18.dp)
                        .clickable { onWishlistClick() }
                )
            }
        }
        //------------Product Content -------------
        SimilarPLPTextContent(product)
    }
}


@Composable
fun SimilarPLPTextContent(product: ProductCardItems) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(product.sizes.size) { index ->
                Text(
                    text = product.sizes[index],
                    style = MaterialTheme.typography.bodySmall,
                    color = buttonBorderColor
                )
            }
        }

        // BRAND
        Text(
            text = product.brand,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
        )

        Spacer(modifier = Modifier.height(6.dp))

        // TITLE
        Text(
            text = product.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = inputTextColor,
        )

        Spacer(modifier = Modifier.height(6.dp))

        // BASE PRICE
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.icon_currency_uae),
                contentDescription = "UAE Currency",
                colorFilter = ColorFilter.tint(
                    color = if (product.rrp.isNotEmpty() && product.discountPrice.isNotEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.size(8.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = product.basePrice,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                color = if (product.rrp.isNotEmpty() && product.discountPrice.isNotEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(8.dp))
            if (product.discountPrice.isNotEmpty()) {
                Image(
                    painter = painterResource(R.drawable.icon_currency_uae),
                    contentDescription = "UAE Currency",
                    colorFilter = ColorFilter.tint(color = nonreturnTxtColor),
                    modifier = Modifier.size(8.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = product.discountPrice,
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    color = nonreturnTxtColor
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        //RRP
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(alpha = if (product.rrp.isEmpty()) 0f else 1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_rrp),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    color = nonreturnTxtColor
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.icon_currency_uae),
                contentDescription = "UAE Currency",
                colorFilter = ColorFilter.tint(color = nonreturnTxtColor)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = product.rrp,
                style = MaterialTheme.typography.bodySmall,
                color = nonreturnTxtColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "(${product.discount} OFF)",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 10.sp
                )
            )

        }
        Spacer(modifier = Modifier.height(8.dp))

        // DELIVERY TIME
        Card(
            modifier = Modifier,
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.arrive_icon),
                    contentDescription = "Delivery Icon",
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = product.delivery,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .padding(all = 4.dp)
                )
            }
        }

    }
}


@Composable
private fun NavigationBarSpacer() {
    Spacer(
        modifier = Modifier
            .height(
                WindowInsets.navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
    )
}

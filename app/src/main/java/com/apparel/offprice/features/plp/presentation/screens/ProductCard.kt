package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.carousel.ImageSliderWithIndicatorPLP
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.productCardColor
import com.apparel.offprice.common.utils.toComposeColorSafe

@Composable
fun ProductCard(
    product: ProductCardItems,
    onWishlistClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)   // Height = Width * 0.75  (change based on Figma)
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
        Spacer(modifier = Modifier.height(12.dp))
        //------------Product Content -------------
        ProductCardTextContent(product)
    }
}

@Composable
fun ProductCardTextContent(product: ProductCardItems) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(product.sizes) { size ->
                Text(
                    text = size,
                    style = MaterialTheme.typography.bodySmall,
                    color = buttonBorderColor
                )
            }
        }

        // BRAND
        Text(
            text = product.brand,
            style = MaterialTheme.typography.labelLarge,
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
                style = MaterialTheme.typography.bodyLarge,
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
        Spacer(modifier = Modifier.height(6.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        product = ProductCardItems(
            id = "1",
            tag = "GOLD LABEL",
            tagContainerColor = "0xFFB47F00",
            tagContentColor = "0xFFFFFFFF",
            image = listOf(
                R.drawable.product_item_1,
                R.drawable.product_item_2,
                R.drawable.product_item_3,
                R.drawable.product_item_4
            ),
            brand = "Nike",
            title = "Nike Air Max 270 React ENG",
            sizes = listOf("UK 6", "UK 7", "UK 8", "UK 9"),
            basePrice = "139.99",
            discountPrice = "149.99",
            rrp = "159.99",
            discount = "20",
            delivery = "GET IT IN 90M",
            isWishlist = false
        ),
        onWishlistClick = { },
        modifier = Modifier
    )
}

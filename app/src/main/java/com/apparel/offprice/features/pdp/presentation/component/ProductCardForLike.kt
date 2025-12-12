package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems

@Composable
fun ProductCardForLike(
    product: ProductCardItems,
    isSelected: Boolean = false,
    onWishlistClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp/2.3f


    val cardBorderColor = if (isSelected) Color(0xFF6A47FF) else Color.Transparent

    Column(
        modifier = modifier
            .width(screenWidth.dp)
            .wrapContentHeight()
            .border(0.dp, cardBorderColor, RoundedCornerShape(5.dp))
            .padding(0.dp)
    ) {

        // ============================
        // IMAGE WITH TAG + WISHLIST
        // ============================
        Box(
            modifier = Modifier
                .width(screenWidth.dp)
                .height(screenWidth.dp*1.3f)
                   // 📌 Height = Width * 0.75  (change based on Figma)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .width(screenWidth.dp)      // Image width = 90% of the card
                    .height(screenWidth.dp*1.3f)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )

            // TAG (TOP-LEFT)
            product.tag?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                        .background(
                            if (it == "GOLD LABEL") Color(0xFFFFD54F)
                            else Color(0xFFE53935),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = it,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        fontSize = 10.sp
                    )
                }
            }

            // WISHLIST ICON (TOP-RIGHT)
            IconButton(
                onClick = onWishlistClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (product.isWishlist) R.drawable.heart_red_icon
                        else R.drawable.heart_icon
                    ),
                    contentDescription = "wishlist",
                    modifier = Modifier.size(22.dp)
                )
            }
        }


        // ============================
        // SIZES ROW
        // ============================
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            product.sizes.forEach { size ->
                Text(
                    text = size,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // BRAND
        Text(
            text = product.brand,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        // TITLE
        Text(
            text = product.title,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(6.dp))

        // PRICE + DISCOUNT
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "฿ ${product.price}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "฿ ${product.rrp}",
                style = MaterialTheme.typography.bodySmall.copy(
                    textDecoration = TextDecoration.LineThrough,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "(${product.discount} OFF)",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFE53935),
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // DELIVERY
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.icon_delivery_address),
                contentDescription = null,
                tint = Color(0xFF6A47FF),
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = product.delivery,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A47FF)
                )
            )
        }
    }
}


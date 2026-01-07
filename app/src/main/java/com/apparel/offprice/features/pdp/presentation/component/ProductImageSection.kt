package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.carousel.ImageSliderWithIndicatorPDP
import com.apparel.offprice.common.utils.toComposeColorSafe
import com.apparel.offprice.features.pdp.data.model.ProductDetailItem


@Composable
fun ProductImageSection(
    pdpdetail: ProductDetailItem,
    modifier: Modifier,
    onShareClick: () -> Unit,
    onClickSimilar: () -> Unit
) {
    Box(modifier = modifier) {
        ImageSliderWithIndicatorPDP(pdpdetail.image)


        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "App Icon"
            )

            Spacer(modifier = Modifier.size(6.dp))

            Card(
                modifier = Modifier
                    .padding(8.dp),
                shape = MaterialTheme.shapes.extraSmall,
                colors = CardDefaults.cardColors(
                    containerColor = pdpdetail.tagContainerColor.toComposeColorSafe(),
                    contentColor = pdpdetail.tagContentColor.toComposeColorSafe()
                )
            ) {
                Text(
                    text = pdpdetail.tag?.uppercase() ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .padding(all = 4.dp)
                )
            }
        }

        ViewSimilarCard(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 30.dp),
            onSimilarClick = {
                onClickSimilar()
            })

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.share_icon),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
                    .clickable() {
                        onShareClick()
                    }
            )

            Spacer(modifier = Modifier.size(12.dp))

            Image(
                painter = painterResource(id = R.drawable.heart_pdpicon),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.size(12.dp))

            Image(
                painter = painterResource(id = R.drawable.cart_icon),
                contentDescription = "share Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
            )
        }

    }

}
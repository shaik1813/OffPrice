package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.features.pdp.data.model.ProductDetailItem
import com.apparel.offprice.features.pdp.presentation.screen.PDPContract


@Composable
fun ProductDescSection(
    pdpDetail: ProductDetailItem,
    modifier: Modifier,
    onSizeGuideClick: () -> Unit,
    onOpenLocationSheet: () -> Unit,
    onTabbyInfoClick: () -> Unit,
    onTamaraInfoClick: () -> Unit,
    event: (PDPContract.UiEvent) -> Unit,
    state: PDPContract.UiState
) {

    Column(
        modifier
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "ADIDAS", fontSize = 16.sp, style = MaterialTheme.typography.bodyLarge)

            Image(
                painter = painterResource(R.drawable.add_to_favicon),
                contentDescription = "fav Icon",
                modifier = Modifier.size(30.dp)
            )
        }

        Text(
            text = "Printed Shirt with Crew Neck and Short Sleeves",
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = inputTextColor,
            modifier = Modifier.padding(top = 4.dp)
        )

        Card(
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
        {
            Text(
                text = "Non-Returnable", fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                color = nonreturnTxtColor, style = MaterialTheme.typography.bodyMedium
            )
        }

        PriceSection()

        Spacer(modifier = Modifier.size(16.dp))

        HorizontalDivider(modifier = Modifier.height(1.dp), color = backgroundColor)

        salesEndSection()

        HorizontalDivider(modifier = Modifier.height(1.dp), color = backgroundColor)

        SizeSelector(onSizeGuideClick = {
            onSizeGuideClick()
        })

        ColorSection(
            pdpDetail,
            selectedColorImg = state.selectedColorImg,
            onColorSelected = { img ->
                event(PDPContract.UiEvent.onChooseColorImg(img))
            })

        OfferCardUI(onClickLocationSheet = {onOpenLocationSheet()})

        PaymentCardUI(
            onTabbyClick = { onTabbyInfoClick() },
            onTamaraClick = { onTamaraInfoClick() })

        FreeDeliveryUI()

        ProductInfoUI()

        MoreBrandUI(stringResource(com.apparel.offprice.R.string.more_from_brand),
            onWishlistClick = {},
            onProductClick = {})

        RecentViewProducts(
            onWishlistClick = {},
            onProductClick = {})
    }

}
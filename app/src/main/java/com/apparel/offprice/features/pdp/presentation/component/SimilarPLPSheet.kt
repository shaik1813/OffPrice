package com.apparel.offprice.features.pdp.presentation.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.features.plp.data.model.ProductCardItems
import com.apparel.offprice.features.plp.data.model.sampleProducts


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimilarPLPSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onWishlistClick: (item: ProductCardItems) -> Unit,
    onProductClick: (item: ProductCardItems) -> Unit
) {
    val heightOfList = LocalConfiguration.current.screenWidthDp.dp - 44.dp

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp)

        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.view_similar).toUpperCase(Locale.current),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 14.sp,
                    color = saleCardColor
                )
                Icon(
                    painter = painterResource(R.drawable.close_24px),
                    contentDescription = null,
                    tint = Color(0xFF677089),
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onDismiss() }
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(borderColor)
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleProducts) { product ->
                    // Each card takes full width inside its column
                    SimilarProductCard(
                        heightOfList,
                        product = product,
                        onWishlistClick = { onWishlistClick(product) },
                        modifier = Modifier
                            .clickable { onProductClick(product) }
                    )
                }
            }
        }
    }
}


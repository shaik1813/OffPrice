package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.saleCardColor

@Composable
fun CartEmptyScreen(
    onStartShoppingClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Cart Icon with Plus
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.empty_carticon), // cart icon
                    contentDescription = null,
                    modifier = Modifier.size(73.dp),
                    tint = Color.Black
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = stringResource(R.string.your_cart_is_empty),
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(800)),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = stringResource(R.string.your_cart_is_waiting_to_be_filled_with_fashion_you_ll_love),
                fontSize = 14.sp,
                color = nonreturnTxtColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500))
            )

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .height(46.dp)
                    .wrapContentWidth()
                    .background(saleCardColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 30.dp)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                    Text(
                        text = stringResource(R.string.start_shopping),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
                        fontSize = 14.sp,
                    )
            }

        }
    }
}

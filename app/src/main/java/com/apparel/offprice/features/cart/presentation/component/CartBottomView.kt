package com.apparel.offprice.features.cart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBottomView(onCheckOutClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        // Add to Bag Button
        Box(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth()
                .background(loginButtonColor, RoundedCornerShape(6.dp))
                .clickable { onCheckOutClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.checkout),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(R.drawable.right_arrow),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 10.dp).height(17.dp).width(17.dp).align(Alignment.CenterVertically)
                )
            }
        }
    }


}


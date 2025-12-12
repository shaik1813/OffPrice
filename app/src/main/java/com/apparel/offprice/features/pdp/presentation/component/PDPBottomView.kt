package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.loginButtonColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PDPBottomView(onSizeSelect: () -> Unit, onAddToBag: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Box(
            modifier = Modifier
                .height(46.dp)
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = loginButtonColor,
                    shape = RoundedCornerShape(6.dp)
                )
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("-", fontSize = 18.sp)
                Text("1", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("+", fontSize = 18.sp)
            }
        }

        // Add to Bag Button
        Box(
            modifier = Modifier
                .height(46.dp)
                .weight(2f)
                .background(loginButtonColor, RoundedCornerShape(6.dp))
                .clickable { onSizeSelect() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.add_to_bag),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 12.sp
            )
        }
    }


}


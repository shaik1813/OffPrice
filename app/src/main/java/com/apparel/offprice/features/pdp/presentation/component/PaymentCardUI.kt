package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.loginButtonColor


@Composable
fun PaymentCardUI(onTabbyClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp,bottom = 2.dp)
            .border(
              width = 1.dp,
              color = backgroundColor,
              shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        Column() {
            Text(
                text = "Pay 4 interest-free payments",
                color = Color(0xFF545454),
                fontSize = 12.sp,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "฿ 35.00",
                color = loginButtonColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }


        Row(verticalAlignment = Alignment.CenterVertically){
           Image(painter = painterResource(R.drawable.tabby_payicon),
               contentDescription = null,
               modifier = Modifier.width(58.dp).height(23.dp)
                   .clickable(interactionSource = null, indication = null){ onTabbyClick() })

            Image(painter = painterResource(R.drawable.info_icon),
                contentDescription = null, modifier = Modifier.padding(2.dp).size(14.dp)
                    .clickable(interactionSource = null, indication = null){ onTabbyClick() })

            Image(painter = painterResource(R.drawable.tamara_payicon),
                contentDescription = null, modifier = Modifier.padding(start = 8.dp).width(68.dp).height(21.dp))

            Image(painter = painterResource(R.drawable.info_icon),
                contentDescription = null, modifier = Modifier.padding(2.dp).size(14.dp))

        }

    }
}
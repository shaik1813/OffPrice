package com.apparel.offprice.features.pdp.presentation.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.sizeCardColor
import com.apparel.offprice.common.theme.sizeDescColor
import com.apparel.offprice.common.theme.stockLeftColor
import com.apparel.offprice.features.pdp.data.model.SizeItem


@Composable
fun SizeSelector(onSizeGuideClick: () -> Unit) {
    val sizes = listOf(
        SizeItem(id ="1","S", 4),
        SizeItem(id ="1","M", 1),
        SizeItem(id ="1","L", 2),
        SizeItem(id ="1","XL", 0),  // Sold out example
        SizeItem(id ="1","XXL", 5),
        SizeItem(id ="1","XXXL", 0, disabled = false)
    )

    var selectedSize by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(top = 16.dp)) {

        /** TITLE + SIZE GUIDE */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row(){
                Text(
                    text = stringResource(com.apparel.offprice.R.string.size),
                    style = MaterialTheme.typography.titleMedium,
                    color = loginButtonColor,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.size(6.dp))

                SizeTypeTabs()
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onSizeGuideClick() }) {
                Image(
                    painter = painterResource(R.drawable.sizeguide_icon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = stringResource(com.apparel.offprice.R.string.size_guide),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleSmall.copy(color = inputTextColor),
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        /** SIZE OPTIONS */
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(sizes) { item ->
                SizeCard(
                    sizeitem = item,
                    isSelected = selectedSize == item.label,
                    onClick = {
                        if (!item.disabled) selectedSize = item.label
                    }
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        /** NOTES */
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Icon(
                    painter = painterResource(id = R.drawable.size_info),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.size_desc),
                    style = MaterialTheme.typography.titleSmall,
                    color = sizeDescColor
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.fiticon),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.size_desc2),
                    style = MaterialTheme.typography.titleSmall,
                    color = sizeDescColor
                )
            }
        }


    }


}

@Composable
fun SizeCard(
    sizeitem: SizeItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        sizeitem.disabled -> sizeCardColor
        isSelected -> sizeCardColor
        else -> sizeCardColor
    }

    val textColor = if (isSelected) loginButtonColor else loginButtonColor

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier
                .height(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(backgroundColor)
                .border(
                    width = if (isSelected) 1.dp else 0.dp,
                    color = if (isSelected) Color.Black else Color.Transparent,
                    shape = RoundedCornerShape(6.dp)
                )
                .clickable(enabled = !sizeitem.disabled) { onClick() },
            contentAlignment = Alignment.Center
        ) {
            if (sizeitem.disabled) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    drawLine(
                        color = Color.White,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = 5f
                    )
                }
            }

            Text(
                text = sizeitem.label,
                color = textColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 15.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (!sizeitem.disabled && sizeitem.stock > 0) {
            Text(
                text = stringResource(com.apparel.offprice.R.string.left, sizeitem.stock),
                color = stockLeftColor,
                fontSize = 10.sp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
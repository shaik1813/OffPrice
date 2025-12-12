package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@Composable
fun SizeGuideScreen(isVisible: Boolean, onDismiss: () -> Unit) {

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .systemBarsPadding()


            ) {
                content() { onDismiss() }
            }
        }
    }
}



@Composable
fun content(onClose: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.size_guide),
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .size(22.dp)
                .clickable {
                    onClose()
                }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .shadow(1.dp)
            .background(Color.Transparent)
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        Spacer(modifier = Modifier.size(20.dp))

        /*Product Image*/
        Row(verticalAlignment = Alignment.Top) {

            Image(
                painter = painterResource(R.drawable.colorimg),
                contentDescription = "Product",
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFE9E9E9))

            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = "ADIDAS",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 14.sp
                )
                Text(
                    text = "Printed Shirt with Crew Neck and Short",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    ),
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // -------- Unit Selector Buttons ------------
        var selectedUnit by remember { mutableStateOf("INCH") }

        Row {
            UnitButton(
                label = "CM",
                selected = selectedUnit == "CM",
                onClick = { selectedUnit = "CM" }
            )

            UnitButton(
                label = "INCH",
                selected = selectedUnit == "INCH",
                onClick = { selectedUnit = "INCH" }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        Column() {
            val modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF4F4F4)),
            ) {
                SizeHeaderCell(modifier, "ALPHA")
                SizeHeaderCell(modifier, "US")
                SizeHeaderCell(modifier, "CHEST")
                SizeHeaderCell(modifier, "WAIST")
            }
        }
        Spacer(modifier = Modifier.height(4.dp))


        SizeRowGuide(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp), "S", "S", "39.4", "31.5"
        )
        SizeRowGuide(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF4F4F4))
                .padding(vertical = 12.dp), "M", "M", "40.9", "33.1"
        )
        SizeRowGuide(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp), "L", "L", "43.3", "35.4"
        )
        SizeRowGuide(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF4F4F4))
                .padding(vertical = 12.dp), "XL", "XL", "45.7", "37.8"
        )
        SizeRowGuide(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp), "XXL", "XXL", "47.2", "39.4"
        )

        HorizontalDivider(
            modifier = Modifier
                .background(Color.Gray)
                .size(1.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun UnitButton(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(0.dp))
            .background(if (selected) Color.Black else Color(0xFFEDEDED))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 24.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleMedium,
            color = if (selected) Color.White else Color.Black
        )
    }
}


@Composable
fun SizeHeaderCell(modifier: Modifier, text: String) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text, style = MaterialTheme.typography.labelMedium)
    }
}


@Composable
fun SizeRowGuide(modifier: Modifier, alpha: String, us: String, chest: String, waist: String) {

    Row(
        modifier = modifier
    ) {
        val modifier = Modifier
            .weight(1f)
        SizeCell(modifier, alpha)
        SizeCell(modifier, us)
        SizeCell(modifier, chest)
        SizeCell(modifier, waist)
    }
}

@Composable
fun SizeCell(modifier: Modifier, text: String) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            ),
            textAlign = TextAlign.Center
        )
    }
}



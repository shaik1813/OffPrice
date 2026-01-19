package com.apparel.offprice.features.plp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceFilterContent(
    min: Float = 0f,
    max: Float = 1000f,
    onRangeChange: (Float, Float) -> Unit
) {

    var from by remember { mutableStateOf("0") }
    var to by remember { mutableStateOf("1000") }

    var sliderRange by remember {
        mutableStateOf(0f..1000f)
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        // 🔹 FROM - TO ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            PriceInputBox(
                value = from,
                onValueChange = {
                    from = it
                    val v = it.toFloatOrNull()
                    if (v != null && v <= sliderRange.endInclusive) {
                        sliderRange = v..sliderRange.endInclusive
                        onRangeChange(v, sliderRange.endInclusive)
                    }
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "to",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.width(12.dp))

            PriceInputBox(
                value = to,
                onValueChange = {
                    to = it
                    val v = it.toFloatOrNull()
                    if (v != null && v >= sliderRange.start) {
                        sliderRange = sliderRange.start..v
                        onRangeChange(sliderRange.start, v)
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 ROUND THUMB SLIDER (BALL TYPE)
        RangeSlider(
            value = sliderRange,
            onValueChange = {
                sliderRange = it
                from = it.start.toInt().toString()
                to = it.endInclusive.toInt().toString()
                onRangeChange(it.start, it.endInclusive)
            },
            valueRange = min..max,
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Black,
                inactiveTrackColor = Color.LightGray,
                thumbColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun PriceInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 🔹 Currency icon / text
        Image(
            painter = painterResource(R.drawable.icon_currency_uae),
            contentDescription = "currency_icon"
        )

        Spacer(modifier = Modifier.width(6.dp))

        BasicTextField(
            value = value,
            onValueChange = {
                if (it.all { ch -> ch.isDigit() }) {
                    onValueChange(it)
                }
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

package com.apparel.offprice.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.profile.presentation.component.CountryCodePicker

@Composable
fun AppPhoneNumberField(
    value: String,
    enabled: Boolean,
    placeholder: String = "",
    phoneCode: Country,
    onValueChange: (String) -> Unit,
    onCountrySelected: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = MaterialTheme.shapes.small)
            .height(42.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            enabled = enabled,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 0.75.dp,
                            color = Color(0xFFB0B0B0),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CountryCodePicker(
                            selected = phoneCode,
                            enabled = enabled,
                            onSelect = { country ->
                                onCountrySelected(country)
                            }
                        )
                        if (value.isEmpty()){
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.bodySmall,
                                color = lineColor
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
        if (!enabled) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = MaterialTheme.shapes.small
                    )
            )
        }
    }
}
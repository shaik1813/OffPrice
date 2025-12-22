package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCleared: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .background(Color.White, shape = RoundedCornerShape(30.dp))
            .height(42.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            enabled = true,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 0.75.dp,
                            color = Color(0xFFB0B0B0),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.icon_search),
                                contentDescription = "SearchIcon",
                                tint = Color(0xFFB0B0B0)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            if (text.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.label_search_for_product),
                                    style = MaterialTheme.typography.displaySmall.copy(
                                        fontSize = 14.sp
                                    ),
                                    color = Color(0xFF8A8A8A)
                                )
                            }
                            innerTextField()
                        }
                        if (text.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "SearchIcon",
                                tint = Color(0xFF8A8A8A),
                                modifier = Modifier
                                    .clickable { onCleared() }
                            )
                        }
                    }
                }
            }
        )
    }
}
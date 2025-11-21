package com.apparel.offprice.features.home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.apparel.offprice.R

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCleared : () -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Text(
                text = stringResource(R.string.label_search_for_product),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = Modifier.fillMaxWidth(0.85f),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "SearchIcon",
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "SearchIcon",
                    modifier = Modifier
                        .clickable{ onCleared() }
                )
            }
        }
    )
}
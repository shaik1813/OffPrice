package com.apparel.offprice.features.authentication.presentation.component


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupEmailField(value: String,
                    onValueChange: (String) -> Unit,
                    placeholder: String) {

    OutlinedTextField(
        value = value,
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, fontSize = 12.sp, color = inputTextColor) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = lineColor,
                shape = RoundedCornerShape(8.dp)
            ).padding(horizontal = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
            errorBorderColor = androidx.compose.ui.graphics.Color.Transparent,
            disabledBorderColor = androidx.compose.ui.graphics.Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )

}



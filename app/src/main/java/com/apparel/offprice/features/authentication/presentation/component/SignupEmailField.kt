package com.apparel.offprice.features.authentication.presentation.component


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor

@Composable
fun SignupEmailField2(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = inputTextColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 13.dp)
    ) {
        // Static placeholder — no animation
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                color = inputTextColor
            ),
            cursorBrush = SolidColor(inputTextColor)
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupEmailField(value: String,
                    onValueChange: (String) -> Unit,
                    placeholder: String) {
    var passwordVisible by remember { mutableStateOf(false) }

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



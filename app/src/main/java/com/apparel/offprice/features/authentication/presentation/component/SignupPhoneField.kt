package com.apparel.offprice.features.authentication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.lineColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPhoneField() {

    var selectedCountry by remember { mutableStateOf("🇦🇪") }
    var countryCode by remember { mutableStateOf("+971") }
    var expanded by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }

    val countries = listOf(
        Triple("🇮🇳", "India", "+91"),
        Triple("🇦🇪", "UAE", "+971"),
        Triple("🇺🇸", "USA", "+1"),
        Triple("🇬🇧", "UK", "+44")
    )

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = { value ->
            if (value.all { it.isDigit() }) phoneNumber = value
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 0.dp),
        placeholder = {
            Text(
                stringResource(R.string.enter_phone),
                fontSize = 12.sp,
                color = inputTextColor
            )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        textStyle = TextStyle(
            fontSize = 12.sp,
        ),
        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {

                // Dropdown trigger
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .clickable { expanded = true }
                ) {
                    Text(text = selectedCountry, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = countryCode, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(6.dp))
                }

                // Divider
                Spacer(modifier = Modifier.width(5.dp))

                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(26.dp)
                        .background(lineColor)
                )

                Spacer(modifier = Modifier.width(6.dp))
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = inputTextColor,
            unfocusedIndicatorColor = inputTextColor,
            cursorColor = inputTextColor,
            focusedTextColor = inputTextColor,
            unfocusedTextColor = Color.Gray
        )
    )

    // Dropdown menu
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        countries.forEach { country ->
            DropdownMenuItem(
                text = { Text("${country.first}  ${country.second}  (${country.third})") },
                onClick = {
                    selectedCountry = country.first
                    countryCode = country.third
                    expanded = false
                }
            )
        }
    }
}

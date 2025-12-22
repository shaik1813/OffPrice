package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@Composable
fun AddressForm() {


    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(R.string.delivery_address),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 10.dp),
            fontSize = 14.sp
        )

        HorizontalDivider(thickness = 1.dp)

        Spacer(modifier = Modifier.height(16.dp))

        FormLabel("Name")
        FormInput("Hazel")

        Spacer(modifier = Modifier.height(16.dp))

        FormLabel("Phone Number")
        PhoneField()

        Spacer(modifier = Modifier.height(16.dp))

        FormLabel("Address")
        FormInput("Sheikh Zayed Road, Building No. 22 Dubai", height = 90.dp)

        Spacer(modifier = Modifier.height(16.dp))

        FormLabel("Area")
        DropdownField("Adhen Village")

        Spacer(modifier = Modifier.height(16.dp))

        FormLabel("City")
        DropdownField("Abu Dhabi")

    }

}


@Composable
fun FormLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
fun FormInput(value: String, height: Dp = 48.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(14.dp)
    ) {
        Text(
            value, color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal
        )
    }
}

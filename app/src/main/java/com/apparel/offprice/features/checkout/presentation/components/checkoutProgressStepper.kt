package com.apparel.offprice.features.checkout.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CheckoutProgressStepper(currentStep: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        StepItem(
            label = "Shipping",
            icons = shippingIcons,
            state = getStepState(currentStep, 1)
        )

        Spacer(modifier = Modifier.width(2.dp))
        StepDivider()
        Spacer(modifier = Modifier.width(2.dp))

        StepItem(
            label = "Payment",
            icons = paymentIcons,
            state = getStepState(currentStep, 2)
        )

        Spacer(modifier = Modifier.width(2.dp))
        StepDivider()
        Spacer(modifier = Modifier.width(2.dp))

        StepItem(
            label = "Confirmation",
            icons = confirmationIcons,
            state = getStepState(currentStep, 3)
        )
    }
}


fun getStepState(current: Int, stepIndex: Int): StepState {
    return when {
        current > stepIndex -> StepState.COMPLETED
        current == stepIndex -> StepState.CURRENT
        else -> StepState.UPCOMING
    }
}

@Composable
fun StepItem(
    label: String,
    icons: StepIcons,
    state: StepState
) {
    val backgroundColor: Color
    val contentColor: Color
    val iconRes: Int

    when (state) {
        StepState.COMPLETED -> {
            backgroundColor = Color(0xFF4CAF50)   // Green
            contentColor = Color.White
            iconRes = icons.completed
        }

        StepState.CURRENT -> {
            backgroundColor = Color.Black
            contentColor = Color.White
            iconRes = icons.current
        }

        StepState.UPCOMING -> {
            backgroundColor = Color.White
            contentColor = Color.Black
            iconRes = icons.upcoming
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {

        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .border(1.dp, Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = contentColor,
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}


@Composable
fun StepDivider() {
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .width(20.dp)
            .background(Color(0xFFE0E0E0))
    )
}


@Preview
@Composable
fun CheckoutProgressStepperPreview() {
    CheckoutProgressStepper(
        currentStep = 1
    )
}
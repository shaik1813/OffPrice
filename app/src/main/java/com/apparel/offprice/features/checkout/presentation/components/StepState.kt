package com.apparel.offprice.features.checkout.presentation.components

import com.apparel.offprice.R

enum class StepState {
    COMPLETED,   // Green
    CURRENT,     // Black
    UPCOMING     // White
}

data class StepIcons(
    val upcoming: Int,      // White background
    val current: Int,       // Black background
    val completed: Int      // Green background
)

val shippingIcons = StepIcons(
    upcoming = R.drawable.location_tick_icon,
    current = R.drawable.location_tick_icon,
    completed = R.drawable.location_tick_icon
)

val paymentIcons = StepIcons(
    upcoming = R.drawable.payment_icon,
    current = R.drawable.payment_icon,
    completed = R.drawable.payment_icon
)

val confirmationIcons = StepIcons(
    upcoming = R.drawable.check_confirmation_icon,
    current = R.drawable.check_confirmation_icon,
    completed = R.drawable.check_confirmation_icon
)

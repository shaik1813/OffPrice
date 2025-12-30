package com.apparel.offprice.common.utils

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor
import java.util.Locale
import androidx.core.graphics.toColorInt


fun String.takeInitials(): String{
    return this.trim()
        .splitToSequence(" ").take(2).map{it.first()}
        .joinToString("").uppercase()
}

private fun formatTwoDecimal(value: Double): String {
    return String.format(Locale.US, "%.2f", value)
}

fun Int.toTwoDecimalString(): String =
    formatTwoDecimal(this.toDouble())

fun Float.toTwoDecimalString(): String =
    formatTwoDecimal(this.toDouble())

fun Double.toTwoDecimalString(): String =
    formatTwoDecimal(this)


fun formatExpiryDate(raw: String): String {
    return when {
        raw.length <= 2 -> raw
        else -> raw.substring(0, 2) + "/" + raw.substring(2)
    }
}

fun String.toComposeColorSafe(default: Color = Color.Transparent): Color {
    val hex = if (startsWith("#")) this else "#$this"
    return try {
        Color(hex.toColorInt())
    } catch (e: Exception) {
        default
    }
}



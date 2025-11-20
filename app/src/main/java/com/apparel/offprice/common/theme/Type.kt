package com.apparel.offprice.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@OptIn(ExperimentalTextApi::class)
val customFontFamily = FontFamily(
    Font(R.font.wixmadefordisplay_regular, weight = FontWeight.Normal),
    Font(R.font.wixmadefordisplay_medium, weight = FontWeight.Medium),
    Font(
        resId = R.font.wixmadefordisplay_semibold,
        weight = FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(FontVariation.weight(600))
    ),
    Font(R.font.wixmadefordisplay_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = (-1).sp
    ),
    titleMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = (-1).sp
    ),
    titleSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = (-1).sp
    )
)